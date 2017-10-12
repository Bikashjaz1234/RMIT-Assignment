ifeq ($(KERNELRELEASE),)  

KERNELDIR ?= /lib/modules/$(shell uname -r)/build 
PWD := $(shell pwd)  

.PHONY: build clean  

build:
	$(MAKE) -C $(KERNELDIR) M=$(PWD) modules  

clean:
	rm -rf *.o *~ core .depend .*.cmd *.ko *.mod.c .tmp_versions/ modules.order Module.symvers 
else  

$(info Building with KERNELRELEASE = ${KERNELRELEASE}) 
obj-m :=    usbmouse.o  

endif
