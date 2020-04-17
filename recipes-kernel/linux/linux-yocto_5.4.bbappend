FILESEXTRAPATHS_prepend := "${THISDIR}/linux:"
SRC_URI += "file://fragment.cfg"

export KBUILD_BUILD_USER ?= "oe-user"
export KBUILD_BUILD_HOST ?= "oe-host"

inherit vmlinux
