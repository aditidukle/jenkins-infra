def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
               sudo rm -rf ${WORKSPACE}/odf
               mkdir ${WORKSPACE}/odf
               cd  ${WORKSPACE}/odf
               echo "-----BEGIN OPENSSH PRIVATE KEY-----
b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAABFwAAAAdzc2gtcn
NhAAAAAwEAAQAAAQEAuI2CMMFyXiHPK4CVNocBXINLzl7BTmxfdtIN3RTtH7dMsJdNLrQo
+JPvkfSdq6HUvCjeubjgdgJOXXvhC9mkH75+2vdULjt1UOtMedZUMmqqtLL22DgTa39fKT
0InC/Y67j4M/HOzfO3G2PZWIdw8iqspEXebu2K9bwx5jQWa0vHKvl6ksLrX50Va22cjS6b
hPq2gY+v3kQt81VuREQbBUFy28r6LZhAbV838obrNX1/kQ+ko0vU/opmXl73dsWggenGhP
axBR6QKL2ayRtfg0HXf6DvHE/V7XYoHImoFncmFxcyP+TUCRsvlX9p+r71h2x5NTIUzWii
SX7jTBBNRwAAA8jrH2xs6x9sbAAAAAdzc2gtcnNhAAABAQC4jYIwwXJeIc8rgJU2hwFcg0
vOXsFObF920g3dFO0ft0ywl00utCj4k++R9J2rodS8KN65uOB2Ak5de+EL2aQfvn7a91Qu
O3VQ60x51lQyaqq0svbYOBNrf18pPQicL9jruPgz8c7N87cbY9lYh3DyKqykRd5u7Yr1vD
HmNBZrS8cq+XqSwutfnRVrbZyNLpuE+raBj6/eRC3zVW5ERBsFQXLbyvotmEBtXzfyhus1
fX+RD6SjS9T+imZeXvd2xaCB6caE9rEFHpAovZrJG1+DQdd/oO8cT9XtdigciagWdyYXFz
I/5NQJGy+Vf2n6vvWHbHk1MhTNaKJJfuNMEE1HAAAAAwEAAQAAAQBT/6yDDnlEmPTXYwYH
NHmNJwFo473uOOtWhJLkznKDHgXc1nC90ihXw9WlxAXDqbvPDHcbZyda7v/GEu1CXIQUIr
tQZIQ/Krbh5sb/KWtXKy+ZYV9y2EsWgXnJpkr1890ypRTrZ6LuIIB2CgNSBQuGXaAIgPiV
2q6F3GuF0K1nleYFMWTw9/vaa2SmvTPi68aaVaapK0ijt2OoRroyIL7BJPrkQLF3mg0i3Q
AQF5DEs6Xio22EMIVj1Ksh/FVhsyOSwStQ4655ywwNv8qyjomm3sAsbFvH4B+yt6rWGI0C
32+DyOD5+fvoMe7Z47o6EP1NeiS4UiV1e5wtrUa4J4BRAAAAgQCUuu9M/Uk03h6sHe5/Fr
v60tPJcPi1dLTBUnPKF4J4IRqNOjZfxCRhbgaV0cnCgeYUxAGiNWGxTquC7pfLf2Iy/a1+
MuD8pkn9Hvn8ORdi1fuV5mS8Cn5gB5vC6WIvrG1bzAnSfMqEXyTDlC9aEby1lbMLpnt0WT
bq5KPmZBChSQAAAIEA8gxr2kOgLQP6CXoeh55qaAmtZzu6Cwe54XzemikDlYwMtMigkvBM
tLAWSj19Qx2ZU40E7vF5gNXkKWli1sV4reRurncVs2/4lvq559/9zqm8j+vmZK1DgFogU/
iZUk4tonuMutqlVl3ZKEQi4d7PV2JQnG3g6alG0TIgUNxqyvkAAACBAMMws31tgBgiKNFO
ulz2Y+3ygd4OqOObz8cC+DF/8ITFSjts3MDjGYoRC+aZrA9NGzRzHoZbssiz54yjN77WY3
t8X2pkT411wjBW8c0Q+mB1nqgtakK37fK8LwPVgJWscUfqh09nSEe1+E6AI2pxKhx4wZe0
YsFk0OFlJX8jt6o/AAAAEXJvb3RANDczYmE1ODkzOWNkAQ==
-----END OPENSSH PRIVATE KEY-----
" > id_rsa
               chmod 0400 id_rsa
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
               cd ocs-upi-kvm; git submodule update --init;
               export KUBECONFIG=${WORKSPACE}/odf/auth/kubeconfig
               oc set data secret/pull-secret -n openshift-config --from-file=.dockerconfigjson=./pull-secret.txt
               export PLATFORM=${PLATFORM}
               export OCP_VERSION=${OCP_RELEASE}
               export OCS_VERSION=${ODF_VERSION}
               export PVS_API_KEY=${IBMCLOUD_API_KEY}
               export RHID_USERNAME=${REDHAT_USERNAME}
               export RHID_PASSWORD=${REDHAT_PASSWORD}
               export PVS_SERVICE_INSTANCE_ID=${SERVICE_INSTANCE_ID}
               export TIER_TEST=${TIER_TEST}
               export OCS_CI_ON_BATION=true
               cd scripts 
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
