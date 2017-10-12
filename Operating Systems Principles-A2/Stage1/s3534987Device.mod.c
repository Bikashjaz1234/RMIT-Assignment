#include <linux/module.h>
#include <linux/vermagic.h>
#include <linux/compiler.h>

MODULE_INFO(vermagic, VERMAGIC_STRING);

__visible struct module __this_module
__attribute__((section(".gnu.linkonce.this_module"))) = {
	.name = KBUILD_MODNAME,
	.init = init_module,
#ifdef CONFIG_MODULE_UNLOAD
	.exit = cleanup_module,
#endif
	.arch = MODULE_ARCH_INIT,
};

static const struct modversion_info ____versions[]
__used
__attribute__((section("__versions"))) = {
	{ 0xaaa11587, __VMLINUX_SYMBOL_STR(module_layout) },
	{ 0xb94af63, __VMLINUX_SYMBOL_STR(class_unregister) },
	{ 0xd39e5855, __VMLINUX_SYMBOL_STR(device_destroy) },
	{ 0xc15089d7, __VMLINUX_SYMBOL_STR(__mutex_init) },
	{ 0xd1d83426, __VMLINUX_SYMBOL_STR(class_destroy) },
	{ 0xe742771, __VMLINUX_SYMBOL_STR(device_create) },
	{ 0x6bc3fbc0, __VMLINUX_SYMBOL_STR(__unregister_chrdev) },
	{ 0x92bbda16, __VMLINUX_SYMBOL_STR(__class_create) },
	{ 0xd914d2c1, __VMLINUX_SYMBOL_STR(__register_chrdev) },
	{ 0x1e047854, __VMLINUX_SYMBOL_STR(warn_slowpath_fmt) },
	{ 0x4f8b5ddb, __VMLINUX_SYMBOL_STR(_copy_to_user) },
	{ 0x88db9f48, __VMLINUX_SYMBOL_STR(__check_object_size) },
	{ 0xd0d8621b, __VMLINUX_SYMBOL_STR(strlen) },
	{ 0x91715312, __VMLINUX_SYMBOL_STR(sprintf) },
	{ 0xa593b47a, __VMLINUX_SYMBOL_STR(mutex_trylock) },
	{ 0x50eedeb8, __VMLINUX_SYMBOL_STR(printk) },
	{ 0xc4f32695, __VMLINUX_SYMBOL_STR(mutex_unlock) },
	{ 0xb4390f9a, __VMLINUX_SYMBOL_STR(mcount) },
};

static const char __module_depends[]
__used
__attribute__((section(".modinfo"))) =
"depends=";


MODULE_INFO(srcversion, "99927BC4D23D2D59E252E81");
