def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
               mkdir ${WORKSPACE}/odf
               cd  ${WORKSPACE}/odf
               echo "{
  "auths":{
    "cloud.openshift.com":{
        "auth":"b3BlbnNoaWZ0LXJlbGVhc2UtZGV2K29jbV9hY2Nlc3NfZGU3YmJmYzY0N2JhNDlhZjg2NDY2ZTM1ZGVmZmI1ZTQ6RlJSRjBFVzdWSFpWOE9TN1ZHU1JGTjcwUU1CRlNSWlJJWDlVMzJEU1FSWElOVlRTU0RLN0dBSDRBSFRTRlA0MA==",
        "email":"lukebrowning@yahoo.com"},
    "quay.io":{
        "auth":"b3BlbnNoaWZ0LXJlbGVhc2UtZGV2K29jbV9hY2Nlc3NfZGU3YmJmYzY0N2JhNDlhZjg2NDY2ZTM1ZGVmZmI1ZTQ6RlJSRjBFVzdWSFpWOE9TN1ZHU1JGTjcwUU1CRlNSWlJJWDlVMzJEU1FSWElOVlRTU0RLN0dBSDRBSFRTRlA0MA==",
        "email":"lukebrowning@yahoo.com"},
    "registry.connect.redhat.com":{
        "auth":"fHVoYy0xajdJNnBENDZWN2JLbGlXMm1ySEtXeXlsSDQ6ZXlKaGJHY2lPaUpTVXpVeE1pSjkuZXlKemRXSWlPaUl6WW1ReE5HRTVOMkU0TnpZME9HRmtPVEJpTmpKa05HVmtaakptTVRCaU9TSjkuSzFjWURNNmVxN0VndV9MeHl1V2RDZFdIaEF4UjZLSFBoazZSSndMbVA5YnVqZnJ3dzh0ZDB6Vjc2RmpTNVJKdTVseVFaSXpSRGZBTlFRSE5GWWJsWWtzQktsdTVQYUdxajJfRnFtbkdYTVJxZ0NCSjV0OFdta0lSM083TFUxSExlRnVKNS05UmlPZjM4eXViOEJuLWN0aTVESFlxR3o3SDczbFhGY3Yya3o0REdoNmVLOUZSNEFCc3BJbDRUREZmMF96QW1SV1ItQmY3LU9BaWNHRUR2NnJIYUVnamRpSHhoaGtsX2xGbk5ZOVNxczJfUW9fWG1jNGg0ZHBxdTl3ajJVTFZtWEpYYlVGYzZPdGRvVGM0X25ibjNhVzJNT3VwdEpTVDA3Y25KMmsta1JkX3BqWnlQV0xxT19pSHhCWm1FOWFYemwxaU45Y2lxd0xDc2pvTGlWRE8tcVNxTDBzQ2oxVlJVVXU5YUtyZ2pUWUY1d2lES1FZd1FRdURteDZ2dDRPeFI4UzNFdENDZ1NtWUtKNzdIRXF5TFJSejNrVXdDbXdoVmJoUktxb19lbml2eDJBbUs5MWw4ZWQ0bnJwYk5pcWljNllFOG1IaEIwdy0yNlBXTmpGRjU3dERKanNHLUJ2UW03TV9Vc2NHbi1wS2tTdG9MR1BWYjU0b2o0VWlPZ1EtT0lMc253dVM4X0ZVeUFzd0RtSWc0Z1B6QXBmX0F1aElCU2c0TlRDUWR4aU10TnpNRl9wNlRVdkdWS1d6dUt2S0kyalFmaWZ2SktTckRsOVIxdXBkcFNfdG5KQW40TUpFQ044TlpHOHBNQWRrUUdmbTVPcUlwTUxPMW1GSVhuN1ctTXFJMF9PcE9VSzFnNDRkR1ZRVmY2QWhLNEgxc1R0VzhGSjcxTm8=",
        "email":"lukebrowning@yahoo.com"},
    "registry.redhat.io":{
        "auth":"fHVoYy0xajdJNnBENDZWN2JLbGlXMm1ySEtXeXlsSDQ6ZXlKaGJHY2lPaUpTVXpVeE1pSjkuZXlKemRXSWlPaUl6WW1ReE5HRTVOMkU0TnpZME9HRmtPVEJpTmpKa05HVmtaakptTVRCaU9TSjkuSzFjWURNNmVxN0VndV9MeHl1V2RDZFdIaEF4UjZLSFBoazZSSndMbVA5YnVqZnJ3dzh0ZDB6Vjc2RmpTNVJKdTVseVFaSXpSRGZBTlFRSE5GWWJsWWtzQktsdTVQYUdxajJfRnFtbkdYTVJxZ0NCSjV0OFdta0lSM083TFUxSExlRnVKNS05UmlPZjM4eXViOEJuLWN0aTVESFlxR3o3SDczbFhGY3Yya3o0REdoNmVLOUZSNEFCc3BJbDRUREZmMF96QW1SV1ItQmY3LU9BaWNHRUR2NnJIYUVnamRpSHhoaGtsX2xGbk5ZOVNxczJfUW9fWG1jNGg0ZHBxdTl3ajJVTFZtWEpYYlVGYzZPdGRvVGM0X25ibjNhVzJNT3VwdEpTVDA3Y25KMmsta1JkX3BqWnlQV0xxT19pSHhCWm1FOWFYemwxaU45Y2lxd0xDc2pvTGlWRE8tcVNxTDBzQ2oxVlJVVXU5YUtyZ2pUWUY1d2lES1FZd1FRdURteDZ2dDRPeFI4UzNFdENDZ1NtWUtKNzdIRXF5TFJSejNrVXdDbXdoVmJoUktxb19lbml2eDJBbUs5MWw4ZWQ0bnJwYk5pcWljNllFOG1IaEIwdy0yNlBXTmpGRjU3dERKanNHLUJ2UW03TV9Vc2NHbi1wS2tTdG9MR1BWYjU0b2o0VWlPZ1EtT0lMc253dVM4X0ZVeUFzd0RtSWc0Z1B6QXBmX0F1aElCU2c0TlRDUWR4aU10TnpNRl9wNlRVdkdWS1d6dUt2S0kyalFmaWZ2SktTckRsOVIxdXBkcFNfdG5KQW40TUpFQ044TlpHOHBNQWRrUUdmbTVPcUlwTUxPMW1GSVhuN1ctTXFJMF9PcE9VSzFnNDRkR1ZRVmY2QWhLNEgxc1R0VzhGSjcxTm8=",
       "email":"lukebrowning@yahoo.com"},
    "sys-powercloud-docker-local.artifactory.swg-devops.com":{
       "auth":"dHJhdmlzcHJAdXMuaWJtLmNvbTpBS0NwNWNiY3ZXczM5TFhCVzRRVUFVWlI0d0FNdTZSV2VDdFl1QVcxQWZtUWdyZHV3QzdOaVY2RE0yamFvWkoxQUtjUkthdUYx"},
    "quay.io/rhceph-dev":{
       "auth": "cmhjZXBoLWRldityZWFkb25seTpEVDlVQUo1V1dKWjlITkRYNkU2VU0xSVZGUTJEWVM5NlVBNkdSODZXWjY2SlBRTUdONkJHMkNLOUlHSENMMjkx",
       "email": ""},
    "brew.registry.redhat.io": {
                        "auth": "fHNoYXJlZC1xZS10ZW1wLnNyYzUuNzViNGQ1OmV5SmhiR2NpT2lKU1V6VXhNaUo5LmV5SnpkV0lpT2lJNU1HRmlOVGMxTlRZME4yWTBOelUyT0RRek56ZG1NR0UwTXpZMFpUTmxaaUo5LlF6YnJsTjQ5TFRIREFKcWFPcEhuVExTRFJfclBLbF9PdjB6VTJWV21OVDRBMUNXZFA3TWR1aDR6azhRbmZUWGtSaXV0UWRIQVJRMUVYMEZpM1pEeDk0aVd2OEpROEc3Ri1Lek1mYm8waTM1c2ROM3kzVXFTekF3QW5DeHVlS3EwZTA5QWRUbFRuS29KOTk3SWVoUnNNMDlSLVNfUlA0MmpIU3pvZWlJcTB2Vml5eUxLSUpNVV9SN1o5cHVqZE1OWHJLcU1hRFk3TnQ1d1c1VURCRm5lcl9Jc3Z5RDVHS2Mtc2FzdzlIdDJvQW5ENlhYajhjc1RKNzlKakFqbjVKOFgyZUE1U3l1elF6UzZSSnc2OXZiZXAyaXFyMl95LVdLeXZpZXNCenlPZzZnNGhvOXE4WXRVRjVKek5KVTRiRmhUSE45Z1BQaEQ5QWZEamx6SnFySjhIcUp4RTcyekpWTnhnM2JXbWV2YkY3NXN1enNMS1VJREJORkpBYXA1eWhEMWxHdUtTN2dhS2M2d3VqNlUtTzRrdmtBaVpRR1NDdUxTaEkxRWk1dEFXcTVEYWhYYlVaZ0l1ZktSZzFfTTBhWmpTU1JRak1JcEZrX1lMYi1HcmwwRDE5TG9vV1gwV1BwUjhDc3VLTFVFTGVFOGNJbEpjUDNlRzJkMV9tTzYzSGJkbEo5QlNaUXh3dWpGd3hwbndReEE3TG1oVXZCdDJOU3V3LVlIVDdkeFN3X3V6YlByX0ZwWmhaQnEzY01XZHhxV0VlOWFtT0xUbnJzbjE4Zms3RWpJd0RWc3hEc1pMRlp6NjhzSlUyRFNFdmdTWEhRcjBWR3lSeHRWcTY2RkJGbEhYaXpjVHY1MXZ2Wk84T0Z5dmxfbXkzamZPUjJOTGdWb09MX3JNRWhyc3JF"
                },
    "registry.svc.ci.openshift.org": {
      "auth": "dXNlcjpfM3JmZmhxYWRrX3Zvd3QydXoxT0NET0VnTk10ZVpoZHBlejJRb2tMTTM4"}
  }
}
" > pull-secret.txt
               #cp  ${WORKSPACE}/deploy/data/pull-secret.txt .
               cp  ${WORKSPACE}/deploy/data/auth.yaml .
               mkdir auth
               scp -o 'StrictHostKeyChecking no' -i id_rsa root@${BASTION_IP}:/root/openstack-upi/auth/* auth/
               echo "export BASTION_IP=169.57.212.156" > .bastion_ip
               echo "export PLATFORM=${PLATFORM}" >> .bastion_ip
               git clone https://github.com/ocp-power-automation/ocs-upi-kvm.git
               cd /root/ocs-upi-kvm; git submodule update --init;
               ssh -o 'StrictHostKeyChecking no' -i id_rsa root@${BASTION_IP} "oc set data secret/pull-secret -n openshift-config --from-file=.dockerconfigjson=/root/pull-secret.txt;"
               echo "export PLATFORM=${PLATFORM}" > env_vars.sh
               echo "export OCP_VERSION=${OCP_RELEASE}" >> env_vars.sh
               echo "export OCS_VERSION=${ODF_VERSION}" >> env_vars.sh
               echo "export PVS_API_KEY=${IBMCLOUD_API_KEY}" >> env_vars.sh
               echo "export RHID_USERNAME=${REDHAT_USERNAME}" >> env_vars.sh
               echo "export RHID_PASSWORD=${REDHAT_PASSWORD}" >> env_vars.sh
               echo "export PVS_SERVICE_INSTANCE_ID=${SERVICE_INSTANCE_ID}" >> env_vars.sh
               echo "export TIER_TEST=${TIER_TEST}" >> env_vars.sh
               echo "export OCS_CI_ON_BATION=true" >> env_vars.sh
               source env_vars.sh
               cd /root/ocs-upi-kvm/scripts 
               ./setup-ocs-ci.sh > setup-ocs-ci.log
               cp setup-ocs-ci.log ${WORKSPACE}/
            '''
        }
        catch (err) {
            echo 'Error ! Setup script failed!'
            env.FAILED_STAGE=env.STAGE_NAME
            throw err
        }
    }
}
