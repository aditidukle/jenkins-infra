#!/bin/bash

git clone https://github.com/ocp-power-automation/ocs-upi-kvm.git
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
./setup-ocs-ci.sh
