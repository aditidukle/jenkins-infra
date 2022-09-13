def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
               scp -i id_rsa -o StrictHostKeyChecking=no ${WORKSPACE}/deploy/data/pull-secret.txt root@${BASTION_IP}:/root/
               scp -i id_rsa -o StrictHostKeyChecking=no ${WORKSPACE}/deploy/data/auth.yaml root@${BASTION_IP}:/root/
               cat << EOF > env_vars.sh
                   export PLATFORM=${PLATFORM};
                   export OCP_VERSION=${OCP_RELEASE};
                   export OCS_VERSION=${ODF_VERSION };
                   export PVS_API_KEY=${IBMCLOUD_API_KEY};
                   export RHID_USERNAME=${REDHAT_USERNAME};
                   export RHID_PASSWORD=${REDHAT_PASSWORD};
                   export PVS_SERVICE_INSTANCE_ID=${SERVICE_INSTANCE_ID};
               EOF
               scp -i id_rsa -o StrictHostKeyChecking=no env_vars.sh root@${BASTION_IP}:/root/
               #ssh -o 'StrictHostKeyChecking no' -i id_rsa root@${BASTION_IP} "git clone https://github.com/ocp-power-automation/ocs-upi-kvm.git"
               #ssh -o 'StrictHostKeyChecking no' -i id_rsa root@${BASTION_IP} "cd /root/ocs-upi-kvm; git submodule update --init;"
               #ssh -o 'StrictHostKeyChecking no' -i id_rsa root@${BASTION_IP} "chmod 0755 env_vars.sh; source env_vars.sh; cd /root/ocs-upi-kvm/scripts; ./setup-ocs-ci.sh > setup-ocs-ci.log;"
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
