def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
               ssh -o 'StrictHostKeyChecking no' -o 'ServerAliveInterval=600' -i ${WORKSPACE}/deploy/id_rsa root@${BASTION_IP} "cd ocs-upi-kvm/scripts/; source /root/env_vars.sh; ./test-ocs-ci.sh --tier ${TIER_TEST} > tier-${TIER_TEST}.log 
               scp -i ${WORKSPACE}/deploy/id_rsa -o StrictHostKeyChecking=no root@${BASTION_IP}:/root/ocs-upi-kvm/scripts/tier-${TIER_TEST}.log ${WORKSPACE}
            '''
        }
        catch (err) {
            echo 'Error ! Tier test script failed!'
            env.FAILED_STAGE=env.STAGE_NAME
            throw err
        }
    }
}
