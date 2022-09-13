def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
               ssh -o 'StrictHostKeyChecking no' -i ${WORKSPACE}/deploy/id_rsa root@${BASTION_IP} "
               cd ocs-upi-kvm
               cd scripts/
               export PLATFORM=${PLATFORM}
               export OCP_VERSION=${OCP_RELEASE}
               export OCS_VERSION=${ODF_VERSION }
               export PVS_API_KEY=${IBMCLOUD_API_KEY}
               export RHID_USERNAME=${REDHAT_USERNAME}
               export RHID_PASSWORD=${REDHAT_PASSWORD}
               export PVS_SERVICE_INSTANCE_ID=${SERVICE_INSTANCE_ID}
               ./test-ocs-ci.sh --tier ${TIER_TEST} > tier-${TIER_TEST}.log     
               "
               scp -i id_rsa -o StrictHostKeyChecking=no root@${BASTION_IP}:/root/ocs-upi-kvm/scripts/tier-${TIER_TEST}.log ${WORKSPACE}
            '''
        }
        catch (err) {
            echo 'Error ! Tier test script failed!'
            env.FAILED_STAGE=env.STAGE_NAME
            throw err
        }
    }
}
