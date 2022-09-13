def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
               scp -i id_rsa -o StrictHostKeyChecking=no ${WORKSPACE}/data/pull-secret.txt root@${BASTION_IP}:/root/
               #scp -i id_rsa -o StrictHostKeyChecking=no ${WORKSPACE}/data/auth.yaml root@${BASTION_IP}:/root/
               #copy auth directory
               ssh -o 'StrictHostKeyChecking no' -i ${WORKSPACE}/deploy/id_rsa root@${BASTION_IP} " git clone https://github.com/ocp-power-automation/ocs-upi-kvm.git
               cd ocs-upi-kvm
               git submodule update --init
               cd scripts/
               export PLATFORM=${PLATFORM}
               export OCP_VERSION=${OCP_RELEASE}
               export OCS_VERSION=${ODF_VERSION }
               export PVS_API_KEY=${IBMCLOUD_API_KEY}
               export RHID_USERNAME=${REDHAT_USERNAME}
               export RHID_PASSWORD=${REDHAT_PASSWORD}
               export PVS_SERVICE_INSTANCE_ID=${SERVICE_INSTANCE_ID}
               ./setup-ocs-ci.sh > setup-ocs-ci.log     
               "
            '''
        }
        catch (err) {
            echo 'Error ! Setup script failed!'
            env.FAILED_STAGE=env.STAGE_NAME
            throw err
        }
    }
}
