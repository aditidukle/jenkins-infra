def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
               scp -i id_rsa -o StrictHostKeyChecking=no ${WORKSPACE}/deploy/data/pull-secret.txt root@${BASTION_IP}:/root/
               scp -i id_rsa -o StrictHostKeyChecking=no ${WORKSPACE}/deploy/data/auth.yaml root@${BASTION_IP}:/root/
               echo "export PLATFORM=${PLATFORM}" > env_vars.sh
               echo "OCP_VERSION=${OCP_RELEASE}" >> env_vars.sh
               echo "OCS_VERSION=${ODF_VERSION }" >> env_vars.sh
               echo "PVS_API_KEY=${IBMCLOUD_API_KEY}" >> env_vars.sh
               echo "RHID_USERNAME=${REDHAT_USERNAME}" >> env_vars.sh
               echo "RHID_PASSWORD=${REDHAT_PASSWORD}" >> env_vars.sh
               echo "PVS_SERVICE_INSTANCE_ID=${SERVICE_INSTANCE_ID};" >> env_vars.sh
               scp -i id_rsa -o StrictHostKeyChecking=no env_vars.sh root@${BASTION_IP}:/root/
               ssh -o 'StrictHostKeyChecking no' -i id_rsa root@${BASTION_IP} "git clone https://github.com/ocp-power-automation/ocs-upi-kvm.git"
               ssh -o 'StrictHostKeyChecking no' -i id_rsa root@${BASTION_IP} "cd /root/ocs-upi-kvm; git submodule update --init;"
               ssh -o 'StrictHostKeyChecking no' -i id_rsa root@${BASTION_IP} "chmod 0755 env_vars.sh; source env_vars.sh; cd /root/ocs-upi-kvm/scripts; ./setup-ocs-ci.sh > setup-ocs-ci.log;"
               scp -i id_rsa -o StrictHostKeyChecking=no root@${BASTION_IP}:/root/ocs-upi-kvm/scripts/setup-ocs-ci.log ${WORKSPACE}
            '''
        }
        catch (err) {
            echo 'Error ! Setup script failed!'
            env.FAILED_STAGE=env.STAGE_NAME
            throw err
        }
    }
}
