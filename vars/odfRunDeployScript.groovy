def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
               ssh -o 'StrictHostKeyChecking no' -i ${WORKSPACE}/deploy/id_rsa root@${BASTION_IP} " cp -r /root/openstack-upi/auth/ /root/
               cp /usr/local/bin/oc /root/ocs-upi-kvm/src/ocs-ci/bin/
               mkdir /root/bin
               cp /usr/local/bin/oc /root/bin/
               oc set data secret/pull-secret -n openshift-config --from-file=.dockerconfigjson=./root/pull-secret.txt
               sleep 60
               cd /root/ocs-upi-kvm/scripts
               export PLATFORM=${PLATFORM}
               export OCP_VERSION=${OCP_RELEASE}
               export OCS_VERSION=${ODF_VERSION }
               export PVS_API_KEY=${IBMCLOUD_API_KEY}
               export RHID_USERNAME=${REDHAT_USERNAME}
               export RHID_PASSWORD=${REDHAT_PASSWORD}
               export PVS_SERVICE_INSTANCE_ID=${SERVICE_INSTANCE_ID}
               ./deploy-ocs-ci.sh > deploy-ocs-ci.log     
               "
               scp -i id_rsa -o StrictHostKeyChecking=no root@${BASTION_IP}:/root/ocs-upi-kvm/scripts/deploy-ocs-ci.log ${WORKSPACE}
            '''
        }
        catch (err) {
            echo 'Error ! ODF deploy script failed!'
            env.FAILED_STAGE=env.STAGE_NAME
            throw err
        }
    }
}
