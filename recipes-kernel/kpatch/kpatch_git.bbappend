COMPATIBLE_HOST .= "|(aarch|aarch64).*-linux"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0003-kpatch-build-allow-specification-of-symvers-file.patch \
	file://0004-debug-fix.patch \
	"
