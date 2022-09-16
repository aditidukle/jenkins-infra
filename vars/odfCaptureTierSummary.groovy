def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
                  FILE=tier${TIER_TEST}.log
                  echo "-----BEGIN OPENSSH PRIVATE KEY-----
b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAABFwAAAAdzc2gtcn
NhAAAAAwEAAQAAAQEAvLAHhG88kld+1MP1QyCuILnntly7MX8AJGHMAUDIH/XBdjoLAlZB
mTAWoe2ePthvGVx2IX/QjiDGOrcDCEgIAhF1yi5jVHYguNCx7QsvUiWFi1CTmiWvuVCJZq
Q/xj4A7WZY455GdzghVU0Lo40RM6nfrtyXRro41psmSdsgGTtMjE3yDc7bXZHvHmOawRVp
IIfL/a7szyxkuZOkDBwwkWlf2y5SoWmayJmR67kcCXANvwHVrItQ3qa6MXEstFq69OXTDQ
QahisrxyF6kRkTZ/MZd7DLPcRC01u3YtmmhI6qlio+suQ6ZMA7AiuKpos0BAPa5shSJR4a
0GmFUZd2jQAAA8gXPFRsFzxUbAAAAAdzc2gtcnNhAAABAQC8sAeEbzySV37Uw/VDIK4gue
e2XLsxfwAkYcwBQMgf9cF2OgsCVkGZMBah7Z4+2G8ZXHYhf9COIMY6twMISAgCEXXKLmNU
diC40LHtCy9SJYWLUJOaJa+5UIlmpD/GPgDtZljjnkZ3OCFVTQujjREzqd+u3JdGujjWmy
ZJ2yAZO0yMTfINzttdke8eY5rBFWkgh8v9ruzPLGS5k6QMHDCRaV/bLlKhaZrImZHruRwJ
cA2/AdWsi1DeproxcSy0Wrr05dMNBBqGKyvHIXqRGRNn8xl3sMs9xELTW7di2aaEjqqWKj
6y5DpkwDsCK4qmizQEA9rmyFIlHhrQaYVRl3aNAAAAAwEAAQAAAQEAj30V2mJ1H5yl+Msk
Qm4hvQVi7k1KlXNM+l3cs7898QAEpRGyuY46yHHHZFC9HHGe2xxzQTpfsRW/aXsvNScB4A
DDIINyShKjA6jBBdCRpdie27jk5j5uiXBzbLjPPsR8FAzW4BHG6Hao8qNqjG+Ee1NdpS5f
ZAvNmXXXAic/G6+revA7wmm48C//I3wRtwaF9Z8/PQ/Ps9geLPNUGK3L31fCCb2SmVMXX0
nz+ufcnuUxwnCZXEMmOzvQxe1IfvodE10YiDwg1zCuCyYb3q3lD+BZH1xObd4u9MnkqiV7
uu3qptsCGfYgH7/fw5UPcA2GH9uC702MBfhIP5RSr+yySQAAAIB4ueQjBVNYI2fUIkJgHN
5SRDpyRGnUBI7wVggU6bWIhcrk8Mcs/yJOORx5sZFsE6qD8Z4bQinsMs0MqfEHPcvNLiH3
qLqaVT/mp/DUb1o21knf9ER9hGBE+PHyZu9JsR8Ylw/l1kSVBB1sTj6Ho5kK871I5VYNw4
pR709qvf/PGwAAAIEA7EGj/craCyHKo5n2BUA0AqzAOcCAlHDQmczIIH81C6t9zE+4Q49X
P6gkFzNXRE/0qN+Ovx/XWkDtCYEviYCROeAqIrfwrRiHaHH2saHhT/LjE4vAojPgKbqCYb
hfHQyun9bEQOO7nrQoNWxjhShG4bZPKOa8RJrsBY6V0/++9BcAAACBAMx0uWQs5vfmLTrg
kr8gCxLovri1+q1KEdsRsFsvxrAgSN8JExrW08ceHBsFKmEv/t5A66/s455bOeP2uxitDM
Nd7e+b4uFyr7Ra6LuTO2bNvp9tMxThrZ/1sx7t/nJUqP/3jc5HrwNbsgaoAx5Ra0biAKmF
Qh/pM4+vQUlk1Xz7AAAAEXJvb3RAMGM4MmFjMWRmZmY2AQ==
-----END OPENSSH PRIVATE KEY-----" > id_rsa
                  scp -i id_rsa -o StrictHostKeyChecking=no root@${BASTION_IP}:/root/tier${TIER_TEST}.log ${WORKSPACE}
                  if [[ -f "$FILE" ]]; then
                    sed -n '/short test summary info/, /Test result:/p' tier${TIER_TEST}.log > tier${TIER_TEST}-summary.txt
                    sed -n '/short test summary info/, /Test result:/p' tier${TIER_TEST}.log | awk '/passed/&&/failed/&&/skipped/' > slacksummary.txt
                    echo "Failures from ODF ${ODF_VERSION} tier ${TIER_TEST}" >> slacksummary.txt
                    sed -n '/short test summary info/, /Test result:/p' tier${TIER_TEST}.log  | grep "FAILED" >> slacksummary.txt
                    cp slacksummary.txt ${WORKSPACE}
                   else
                     echo "tier${TIER_TEST}.log file not found" > slacksummary.txt
                   fi
            '''
            slacksummary = readFile 'slacksummary.txt'
            env.MESSAGE = "Tier test summary:`${slacksummary}`"
        }
        catch (err) {
            echo 'Error ! Capturing tier test summary failed!'
            env.FAILED_STAGE=env.STAGE_NAME
            throw err
        }
    }
}
