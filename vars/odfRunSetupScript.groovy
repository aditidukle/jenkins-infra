def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
               echo "-----BEGIN OPENSSH PRIVATE KEY-----
b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAABFwAAAAdzc2gtcn
NhAAAAAwEAAQAAAQEAudPRftavUQW/NIe71iPxaEt31FD5LTLhud7Jjs7wgPpIo6lcMJGP
UFU87fv/z+cTb5TGYgz17wwrhui4ViZUiAgKSBxsiC/DTq4K1JYRHO/u1P9xQLdziHaFZi
xGjTMgEBPh8eDoGdbV8hr/7hjFYvD+a+4g0KY3ZVkLj9o5FHgFV5ZRPSTk1fosUy3BvdsT
ar+wCoTohWopCpTA9ORiVo0gHTTyqSfOush4ZDQZqURhD70QX90ej7VnaWHMwB99cjmdnM
5YHkthXIxct3Jl6GWM8PEPeNBhQa8qXoHwKFyUi7U8cKa8MV5dzXto0OJF82Xp6J2aPmdj
X6KI9kS5+QAAA8jYukxa2LpMWgAAAAdzc2gtcnNhAAABAQC509F+1q9RBb80h7vWI/FoS3
fUUPktMuG53smOzvCA+kijqVwwkY9QVTzt+//P5xNvlMZiDPXvDCuG6LhWJlSICApIHGyI
L8NOrgrUlhEc7+7U/3FAt3OIdoVmLEaNMyAQE+Hx4OgZ1tXyGv/uGMVi8P5r7iDQpjdlWQ
uP2jkUeAVXllE9JOTV+ixTLcG92xNqv7AKhOiFaikKlMD05GJWjSAdNPKpJ866yHhkNBmp
RGEPvRBf3R6PtWdpYczAH31yOZ2czlgeS2FcjFy3cmXoZYzw8Q940GFBrypegfAoXJSLtT
xwprwxXl3Ne2jQ4kXzZenonZo+Z2Nfooj2RLn5AAAAAwEAAQAAAQA5uK75TCxW6YcUBRl2
keGZuN925tQPkx+Egcxv/J2+IoBPvfqx53TxyhhMABvc83UZEshwioAWCdy3YjBoKi3Vt1
GiRpn7z4JGPU+F6ztTlr/ED7iZFqLff5k+PICJuZi7BcIIRQkzhYUdV/+pcK5XZm5RDj3J
MehBJeCuhWV3kqxJeVaiHxXTOLepClR/+ds/TkNCFd5+vuaZDBQKg48UVCV3BxLsteJDMp
Q+3W1BdSpHvfz4xGj9udOFNgmZgsOwxhvOHp7Ee/AaLExVh2wlTd0GBr867Nu+E4ZLb1lw
aNHJ07sOSZXYvQ+lHCGtL2oHbhMVbX5lqqG3BgxGdYKRAAAAgQDd27+8XujAtho9UQsBBJ
oM87QHseMNsRoKcgLaWIGuRYyWNi0CUH+fFKIwbNhaYE+2fwCv+EocrQ2Y9wNW8hxMPyrd
3VAvRFttrBhhTTg2Sc/fc4WXYZB9d3Pm0LR0n/7VpkTvtkmphV2ZwyiHuw56QC5RqnW+lg
3XytIiZN/WOwAAAIEA8GzOdeY6rbMW1JgDuqCM7gg44JXgJtFn2mYLtEgeFH3BR2NWm5PU
pU0jju+8+2xsXy6KY7lHS4HFYS56JH+NOolKk+lo45Apdd/c53gq5uOgODCXC3uyGhgJN8
pPBxPSaQQicJW8nzYfv/X4jsAYj4f5nEZHBZ6aM1peWeRNlS0AAACBAMXdkYqv1wnSowU2
qyWBc8giovKDB0QaiEUccnM++5RCNs3VXUpsJhD6/r705z1wYrTEiPI1g44ji236TCNY4B
BJYMOAVZYeM5GLpS1q/rAd7zDcuZLjecTkxMppxgVD2Hmw4KPqP6M/ClkNMulxA8VmJxGK
vT/Wh6Pe44mFAk99AAAAEXJvb3RAZGU1ODAyNjcwNDRkAQ==
-----END OPENSSH PRIVATE KEY-----"  > id_rsa
               chmod 0400 id_rsa
               scp -i id_rsa -o StrictHostKeyChecking=no ${WORKSPACE}/deploy/data/pull-secret.txt root@${BASTION_IP}:/root/
               scp -i id_rsa -o StrictHostKeyChecking=no ${WORKSPACE}/deploy/data/auth.yaml root@${BASTION_IP}:/root/
               scp -o 'StrictHostKeyChecking no' -i id_rsa ${WORKSPACE}/scripts/odf-run-setup.sh root@${BASTION_IP}:
               ssh -o 'StrictHostKeyChecking no' -i id_rsa root@${BASTION_IP} "chmod 755 odf-run-setup.sh;
                                                                                ./odf-run-setup.sh > setup.log
                                                                                exit"
               #scp -i id_rsa -o StrictHostKeyChecking=no root@${BASTION_IP}:/root/ocs-upi-kvm/scripts/setup-ocs-ci.log ${WORKSPACE}
            '''
        }
        catch (err) {
            echo 'Error ! Setup script failed!'
            env.FAILED_STAGE=env.STAGE_NAME
            throw err
        }
    }
}
