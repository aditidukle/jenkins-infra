def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
               ssh -o 'StrictHostKeyChecking no' -i ${WORKSPACE}/deploy/id_rsa root@${BASTION_IP} "cp -r /root/openstack-upi/auth/ /root/; cp /usr/local/bin/oc /root/ocs-upi-kvm/src/ocs-ci/bin/; mkdir /root/bin; cp /usr/local/bin/oc /root/bin/;"
               ssh -o 'StrictHostKeyChecking no' -o 'ServerAliveInterval=600' -i ${WORKSPACE}/deploy/id_rsa root@${BASTION_IP} "source /root/env_vars.sh; cd /root/ocs-upi-kvm/scripts;  ./deploy-ocs-ci.sh > deploy-ocs-ci.log" 
               scp -i ${WORKSPACE}/deploy/id_rsa -o StrictHostKeyChecking=no root@${BASTION_IP}:/root/ocs-upi-kvm/scripts/deploy-ocs-ci.log ${WORKSPACE}
            '''
        }
        catch (err) {
            echo 'Error ! ODF deploy script failed!'
            env.FAILED_STAGE=env.STAGE_NAME
            throw err
        }
    }
}
