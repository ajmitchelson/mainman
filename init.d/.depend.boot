TARGETS = mountkernfs.sh udev mountdevsubfs.sh bootlogd keyboard-setup lvm2 hostname.sh hwclockfirst.sh checkroot.sh hwclock.sh ifupdown-clean module-init-tools mtab.sh checkfs.sh ifupdown nfs-common mountall.sh mountall-bootclean.sh mountoverflowtmp networking urandom procps x11-common udev-mtab mountnfs.sh portmap mountnfs-bootclean.sh kbd console-setup bootmisc.sh stop-bootlogd-single
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
nfs-common: hwclock.sh
mountall.sh: lvm2 checkfs.sh
mountall-bootclean.sh: mountall.sh
mountoverflowtmp: mountall-bootclean.sh
networking: mountkernfs.sh mountall.sh mountoverflowtmp ifupdown
urandom: mountall.sh mountoverflowtmp
procps: mountkernfs.sh mountall.sh mountoverflowtmp udev module-init-tools bootlogd
x11-common: mountall.sh mountoverflowtmp
udev-mtab: udev mountall.sh mountoverflowtmp
mountnfs.sh: mountall.sh mountoverflowtmp networking ifupdown nfs-common
portmap: networking ifupdown mountall.sh mountoverflowtmp
mountnfs-bootclean.sh: mountall.sh mountoverflowtmp mountnfs.sh
kbd: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh
console-setup: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh kbd
bootmisc.sh: mountall.sh mountoverflowtmp mountnfs.sh mountnfs-bootclean.sh udev
stop-bootlogd-single: mountall.sh mountoverflowtmp udev keyboard-setup console-setup networking ifupdown nfs-common mountnfs.sh mountnfs-bootclean.sh hwclock.sh checkroot.sh ifupdown-clean portmap lvm2 mountdevsubfs.sh checkfs.sh urandom mountkernfs.sh hostname.sh hwclockfirst.sh kbd mountall-bootclean.sh procps module-init-tools x11-common mtab.sh bootlogd bootmisc.sh udev-mtab
