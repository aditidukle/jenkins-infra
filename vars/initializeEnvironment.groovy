def call(String region="us-south",resource_group="ibm-internal-cicd-resource-group") {
    script {
      ansiColor('xterm') {
           echo ""
      }
      try {
          env.REGION=region
          env.RESOURCE_GROUP=resource_group
          if ( env.SERVICE_INSTANCE_GUID != ""  ){
              env.SERVICE_INSTANCE_ID=env.SERVICE_INSTANCE_GUID
          }
           sh '''
            echo 'Initializing supporting repos and keys !'
            cd ${WORKSPACE}/deploy
            make init
            make keys
            make setup-dependencies
            #check if ibmcloud cli exist
            ibmcloud -v
            if [ $? -ne 0 ]; then
               apt update; apt-get install -y wget curl; \
               wget https://download.clis.cloud.ibm.com/ibm-cloud-cli/2.1.1/IBM_Cloud_CLI_2.1.1_amd64.tar.gz --no-check-certificate; \
               curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"; \
               install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl; \
               tar -xvzf "./IBM_Cloud_CLI_2.1.1_amd64.tar.gz"; \
               ./Bluemix_CLI/install; \
               ibmcloud update -f; \
               ibmcloud config --check-version false
            fi
            if [ "${POWERVS}" = "true" ] ; then
                ibmcloud update -f
                ibmcloud plugin update --all
                curl -sL https://raw.githubusercontent.com/ppc64le-cloud/pvsadm/master/get.sh | VERSION="v0.1.8" FORCE=1 bash
                ibmcloud login -a cloud.ibm.com -r ${REGION} -g ${RESOURCE_GROUP} -q --apikey=${IBMCLOUD_API_KEY}
                ibmcloud target -r ${REGION} -g ${RESOURCE_GROUP}
                CRN=$(ibmcloud pi service-list  | grep "${SERVICE_INSTANCE_ID}" |awk '{print $1}')
                ibmcloud pi service-target "$CRN"
            fi            
           '''
      }
      catch (err) {
           echo 'Error ! ENV setup failed!'
           env.FAILED_STAGE=env.STAGE_NAME
           throw err
      }
   }
}
