#!/bin/bash
# ignore mac resources when using tar,zip,etc...
#
export COPYFILE_DISABLE=true
export BASE=`dirname $0`

# Release Configuration
export INSTALLER=${BASE}/installer
export TARGET=${BASE}/target
#export TARGET=${BASE}/../features/net.refractions.udig-product/target/products/
export VERSION=1.3-SNAPSHOT
export BUILD=${BASE}/build

# net.refractions.udig.libs "qualifier" for SDK (used to update libs source reference
# example: TAG=1.3.2
# example: QUALIFIER=1.3.2.201201031509
export TAG=1.3.1

# grab the qualifier after doing your target export; it is based on todays date
export QUALIFIER=1.3.1.201201031509

# Build Resources
export JRE=${BASE}/jre

export JRE_WIN32=jre1.6.0_25.win32_gdal_ecw
export JRE_WIN64=jre1.6.0.win64
export JRE_LIN32=jre1.6.0_25.lin32_gdal_ecw
export JRE_LIN64=jre1.6.0_25.lin64_gdal_ecw

echo "Release Version:  ${VERSION}"
echo "Available for release:"
ls ${TARGET}