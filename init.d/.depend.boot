TARGETS = mountkernfs.sh udev mountdevsubfs.sh bootlogd keyboard-setup lvm2 hostname.sh hwclockfirst.sh checkroot.sh hwclock.sh ifupdown-clean module-init-tools mtab.sh checkfs.sh ifupdown mountall.sh mountall-bootclean.sh mountoverflowtmp networking urandom procps udev-mtab portmap nfs-common mountnfs.sh mountnfs-bootclean.sh kbd console-setup bootmisc.sh stop-bootlogd-single
INTERACTIVE = udev keyboard-setup checkroot.sh checkfs.sh kbd console-setup
udev: mountkernfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
bootlogd: mountdevsubfs.sh
keyboard-setup: bootlogd mountkernfs.sh udev
lvm2: mountdevsubfs.sh udev bootlogd
hostname.sh: bootlogd
hwclockfirst.sh: mountdevsubfs.sh bootlogd
checkroot.sh: hwclockfirst.sh mountdevsubfs.sh hostname.sh bootlogd keyboard-setup
hwclock.sh: checkroot.sh bootlogd
ifupdown-clean: checkroot.sh
module-init-tools: checkroot.sh
mtab.sh: checkroot.sh
checkfs.sh: lvm2 checkroot.sh mtab.sh
ifupdown: ifupdown-clean
mountall.sh: lvm2 checkfs.sh
mountall-bootclean.sh: mountall.sh
mountoverflowtmp: mountall-bootclean.sh
networking: mountkernfs.sh mountall.sh mountoverflowtmp ifupdown
urandom: mountall.sh mountoverflowtmp
procps: mountkernfs.sh mountall.sh mountoverflowtmp udev module-init-tools bootlogd
udev-mtab: udev mountall.sh mountoverflowtmp
portmap: networking ifupdown mountall.sh mountoverflowtmp
nfs-common: portmap hwclock.sh
mountnfs.sh: mountall.sh mountoverflowtmp networking ifupdown portmap nfs-common
mountnfs-bootclean.sh: mountall.sh mountoverflowtmp mountnfs.sh
kbd: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh
console-setup: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh kbd
bootmisc.sh: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh udev
stop-bootlogd-single: mountall.sh mountoverflowtmp udev keyboard-setup console-setup portmap networking ifupdown mountnfs.sh mountnfs-bootclean.sh hwclock.sh lvm2 mountdevsubfs.sh checkfs.sh checkroot.sh mountkernfs.sh ifupdown-clean urandom nfs-common hostname.sh hwclockfirst.sh mountall-bootclean.sh procps module-init-tools bootmisc.sh mtab.sh bootlogd kbd udev-mtab
