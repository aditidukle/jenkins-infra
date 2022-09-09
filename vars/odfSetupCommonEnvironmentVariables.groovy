def call() {
    script {
        //Failed stage
        env.FAILED_STAGE=""
        //VMs setup
        if ( env.POWERVS == "true" ) {
            env.INSTANCE_NAME = "rdr-cicd"
            env.NETWORK_NAME = "ocp-net"
            env.RHEL_USERNAME = "root"
            env.RHEL_SMT = "4"
            env.CLUSTER_DOMAIN = "redhat.com"
            env.SYSTEM_TYPE = "s922"
            env.ENABLE_LOCAL_REGISTRY = "false"
            env.LOCAL_REGISTRY_IMAGE = "docker.io/ibmcom/registry-ppc64le:2.6.2.5"
            env.SETUP_SQUID_PROXY = "true"
            //Needed for target service
            env.CRN = "crn:v1:bluemix:public:power-iaas:tor01:a/7cfbd5381a434af7a09289e795840d4e:007e0e92-91d5-4f30-bc63-ca515660a4c2::"

            // Bellow 4 variables are not used. Disabled in template
            env.HELPERNODE_REPO = "https://github.com/RedHatOfficial/ocp4-helpernode"
            env.HELPERNODE_TAG = "324e09e3d303101874f540730c993cd986ddbc04"
            env.INSTALL_PLAYBOOK_REPO = "https://github.com/ocp-power-automation/ocp4-playbooks"
            switch (env.OCP_RELEASE) {
                case "4.11":
                    env.INSTALL_PLAYBOOK_TAG = "284b597b3e88c635e3069b82926aa16812238492"
                    break
                case "4.10":
                    env.INSTALL_PLAYBOOK_TAG = "284b597b3e88c635e3069b82926aa16812238492"
                    break
                case "4.9":
                    env.INSTALL_PLAYBOOK_TAG = "284b597b3e88c635e3069b82926aa16812238492"
                    break
                case "4.8":
                    env.INSTALL_PLAYBOOK_TAG = "284b597b3e88c635e3069b82926aa16812238492"
                    break
                case "4.7":
                    env.INSTALL_PLAYBOOK_TAG = "de8b4bf5f243f40dae91a3a0cc67a55c571d210e"
                    break
                case "4.6":
                    env.INSTALL_PLAYBOOK_TAG = "2888fad354e72af39af1be4f75efaea224187b6b"
                    break
                default:
                     env.INSTALL_PLAYBOOK_TAG = "284b597b3e88c635e3069b82926aa16812238492"
            }

            //Upgrade variables
            env.UPGRADE_IMAGE = ""
            env.UPGRADE_PAUSE_TIME = ""
            env.UPGRADE_DELAY_TIME = ""


            //E2e Variables
            env.E2E_GIT = "https://github.com/openshift/origin"
            env.E2E_BRANCH="release-${env.OCP_RELEASE}"
            if (OCP_RELEASE == "4.5" || OCP_RELEASE == "4.6" ) {
                env.E2E_EXCLUDE_LIST = "https://raw.github.ibm.com/redstack-power/e2e-exclude-list/${env.OCP_RELEASE}-powervm/ocp${env.OCP_RELEASE}_power_exclude_list.txt"
            }
            else{
                env.E2E_EXCLUDE_LIST = "https://raw.github.ibm.com/redstack-power/e2e-exclude-list/${env.OCP_RELEASE}-powervs/ocp${env.OCP_RELEASE}_power_exclude_list.txt"
            }

            //Scale variables
            env.SCALE_NUM_OF_DEPLOYMENTS = "60"
            env.SCALE_NUM_OF_NAMESPACES = "1000"

            //Slack message
            env.MESSAGE=""

            env.DEPLOYMENT_STATUS = false
            env.BASTION_IP = ""

            //Pull Secret
            env.PULL_SECRET_FILE = "${WORKSPACE}/deploy/data/pull-secret.txt"
            env.OPENSHIFT_INSTALL_TARBALL="https://mirror.openshift.com/pub/openshift-v4/ppc64le/clients/ocp-dev-preview/latest-${OCP_RELEASE}/openshift-install-linux.tar.gz"
            env.OPENSHIFT_CLIENT_TARBALL="https://mirror.openshift.com/pub/openshift-v4/ppc64le/clients/ocp-dev-preview/latest-${OCP_RELEASE}/openshift-client-linux.tar.gz"
            env.OPENSHIFT_CLIENT_TARBALL_AMD64="https://mirror.openshift.com/pub/openshift-v4/clients/ocp-dev-preview/latest-${OCP_RELEASE}/openshift-client-linux.tar.gz"
        }
    }
}
