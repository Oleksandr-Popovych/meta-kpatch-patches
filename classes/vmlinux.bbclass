inherit vmlinux-base

do_vmlinux_access () {
	install -d ${VMLINUX_LOCATION}
	install -m 0644 ${B}/vmlinux ${VMLINUX_LOCATION}/vmlinux-${KERNEL_VERSION}
}

addtask do_vmlinux_access before do_install after do_compile
