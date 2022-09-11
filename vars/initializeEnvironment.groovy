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
           '''
      }
      catch (err) {
           echo 'Error ! ENV setup failed!'
           env.FAILED_STAGE=env.STAGE_NAME
           throw err
      }
   }
}
