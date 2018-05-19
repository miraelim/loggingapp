sudo modprobe tpm_tis_spi
sudo systemctl start tpm2-abrmd
sudo service trousers start
#export TPM2TOOLS_DEVICE_FILE=/dev/tpm0

