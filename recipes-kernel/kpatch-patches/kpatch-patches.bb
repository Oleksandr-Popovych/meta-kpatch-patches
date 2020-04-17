SUMMARY = ""
DESCRIPTION = ""

LICENSE = "CLOSED"

DEPENDS += "kpatch kpatch-build-native virtual/kernel gcc make bc-native bison-native"
RDEPENDS_${PN} += "kpatch"

SRC_URI = "file://kpatch-patches/*.patch;downloadfilename=/kpatch-patches/*"
B = "${WORKDIR}/kpatch-patches-build"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

inherit module-base vmlinux-base

LIVEPATCH_MODULES_PATH = "/home/root/livepatch-modules"
KBUILD_OUTPUT = "${S}/src"

do_configure_class-target() {
	cp -a "${STAGING_KERNEL_DIR}/." "${S}/src/"
	cp "${STAGING_KERNEL_BUILDDIR}/Module.symvers" "${S}" 
	cp -r "${STAGING_KERNEL_BUILDDIR}/boot" "${S}"
	cp "${STAGING_KERNEL_BUILDDIR}/.config" "${S}"
}

do_compile_class-target() {
	for patch in `ls ${WORKDIR}/kpatch-patches`;
	do
		patch="${patch%".patch"}";
		kpatch-build -v ${VMLINUX_LOCATION}/vmlinux-${KERNEL_VERSION} -s ${S}/src -o ${B} -n ${patch} --config ${S}/.config --symvers ${S}/Module.symvers --cross-compile ${TARGET_PREFIX} --debug "${WORKDIR}/kpatch-patches/${patch}.patch";
	done
}

do_install_class-target() {
	install -d "${D}${LIVEPATCH_MODULES_PATH}";
	install -m 0511 ${B}/* ${D}${LIVEPATCH_MODULES_PATH};
}

FILES_${PN} = "${LIVEPATCH_MODULES_PATH}/*.ko"

BBCLASSEXTEND += "native"
