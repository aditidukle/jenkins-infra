def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
               #scp -i ${WORKSPACE}/deploy/id_rsa -o StrictHostKeyChecking=no ${WORKSPACE}/deploy/data/pull-secret.txt root@${BASTION_IP}:/root/
               echo "-----BEGIN OPENSSH PRIVATE KEY-----
b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAABFwAAAAdzc2gtcn
NhAAAAAwEAAQAAAQEAuI2CMMFyXiHPK4CVNocBXINLzl7BTmxfdtIN3RTtH7dMsJdNLrQo
+JPvkfSdq6HUvCjeubjgdgJOXXvhC9mkH75+2vdULjt1UOtMedZUMmqqtLL22DgTa39fKT
0InC/Y67j4M/HOzfO3G2PZWIdw8iqspEXebu2K9bwx5jQWa0vHKvl6ksLrX50Va22cjS6b
hPq2gY+v3kQt81VuREQbBUFy28r6LZhAbV838obrNX1/kQ+ko0vU/opmXl73dsWggenGhP
axBR6QKL2ayRtfg0HXf6DvHE/V7XYoHImoFncmFxcyP+TUCRsvlX9p+r71h2x5NTIUzWii
SX7jTBBNRwAAA8jrH2xs6x9sbAAAAAdzc2gtcnNhAAABAQC4jYIwwXJeIc8rgJU2hwFcg0
vOXsFObF920g3dFO0ft0ywl00utCj4k++R9J2rodS8KN65uOB2Ak5de+EL2aQfvn7a91Qu
O3VQ60x51lQyaqq0svbYOBNrf18pPQicL9jruPgz8c7N87cbY9lYh3DyKqykRd5u7Yr1vD
HmNBZrS8cq+XqSwutfnRVrbZyNLpuE+raBj6/eRC3zVW5ERBsFQXLbyvotmEBtXzfyhus1
fX+RD6SjS9T+imZeXvd2xaCB6caE9rEFHpAovZrJG1+DQdd/oO8cT9XtdigciagWdyYXFz
I/5NQJGy+Vf2n6vvWHbHk1MhTNaKJJfuNMEE1HAAAAAwEAAQAAAQBT/6yDDnlEmPTXYwYH
NHmNJwFo473uOOtWhJLkznKDHgXc1nC90ihXw9WlxAXDqbvPDHcbZyda7v/GEu1CXIQUIr
tQZIQ/Krbh5sb/KWtXKy+ZYV9y2EsWgXnJpkr1890ypRTrZ6LuIIB2CgNSBQuGXaAIgPiV
2q6F3GuF0K1nleYFMWTw9/vaa2SmvTPi68aaVaapK0ijt2OoRroyIL7BJPrkQLF3mg0i3Q
AQF5DEs6Xio22EMIVj1Ksh/FVhsyOSwStQ4655ywwNv8qyjomm3sAsbFvH4B+yt6rWGI0C
32+DyOD5+fvoMe7Z47o6EP1NeiS4UiV1e5wtrUa4J4BRAAAAgQCUuu9M/Uk03h6sHe5/Fr
v60tPJcPi1dLTBUnPKF4J4IRqNOjZfxCRhbgaV0cnCgeYUxAGiNWGxTquC7pfLf2Iy/a1+
MuD8pkn9Hvn8ORdi1fuV5mS8Cn5gB5vC6WIvrG1bzAnSfMqEXyTDlC9aEby1lbMLpnt0WT
bq5KPmZBChSQAAAIEA8gxr2kOgLQP6CXoeh55qaAmtZzu6Cwe54XzemikDlYwMtMigkvBM
tLAWSj19Qx2ZU40E7vF5gNXkKWli1sV4reRurncVs2/4lvq559/9zqm8j+vmZK1DgFogU/
iZUk4tonuMutqlVl3ZKEQi4d7PV2JQnG3g6alG0TIgUNxqyvkAAACBAMMws31tgBgiKNFO
ulz2Y+3ygd4OqOObz8cC+DF/8ITFSjts3MDjGYoRC+aZrA9NGzRzHoZbssiz54yjN77WY3
t8X2pkT411wjBW8c0Q+mB1nqgtakK37fK8LwPVgJWscUfqh09nSEe1+E6AI2pxKhx4wZe0
YsFk0OFlJX8jt6o/AAAAEXJvb3RANDczYmE1ODkzOWNkAQ==
-----END OPENSSH PRIVATE KEY-----
" > id_rsa
               chmod 0400 id_rsa               
               scp -i id_rsa -o StrictHostKeyChecking=no ${WORKSPACE}/deploy/data/auth.yaml root@${BASTION_IP}:/root/
               ssh -o 'StrictHostKeyChecking no' -i id_rsa root@${BASTION_IP} "oc set data secret/pull-secret -n openshift-config --from-file=.dockerconfigjson=/root/pull-secret.txt;"
               echo "export PLATFORM=${PLATFORM}" > env_vars.sh
               echo "export OCP_VERSION=${OCP_RELEASE}" >> env_vars.sh
               echo "export OCS_VERSION=${ODF_VERSION}" >> env_vars.sh
               echo "export PVS_API_KEY=${IBMCLOUD_API_KEY}" >> env_vars.sh
               echo "export RHID_USERNAME=${REDHAT_USERNAME}" >> env_vars.sh
               echo "export RHID_PASSWORD=${REDHAT_PASSWORD}" >> env_vars.sh
               echo "export PVS_SERVICE_INSTANCE_ID=${SERVICE_INSTANCE_ID}" >> env_vars.sh
               echo "export TIER_TEST=${TIER_TEST}" >> env_vars.sh
               scp -i id_rsa -o StrictHostKeyChecking=no env_vars.sh root@${BASTION_IP}:/root/
               ssh -o 'StrictHostKeyChecking no' -i id_rsa root@${BASTION_IP} "git clone https://github.com/ocp-power-automation/ocs-upi-kvm.git"
               ssh -o 'StrictHostKeyChecking no' -i id_rsa root@${BASTION_IP} "cd /root/ocs-upi-kvm; git submodule update --init;"
               ssh -o 'StrictHostKeyChecking no' -i id_rsa root@${BASTION_IP} "chmod 0755 env_vars.sh; source env_vars.sh; cd /root/ocs-upi-kvm/scripts; ./setup-ocs-ci.sh > setup-ocs-ci.log;"
               scp -i id_rsa -o StrictHostKeyChecking=no root@${BASTION_IP}:/root/ocs-upi-kvm/scripts/setup-ocs-ci.log .
            '''
        }
        catch (err) {
            echo 'Error ! Setup script failed!'
            env.FAILED_STAGE=env.STAGE_NAME
            throw err
        }
    }
}
