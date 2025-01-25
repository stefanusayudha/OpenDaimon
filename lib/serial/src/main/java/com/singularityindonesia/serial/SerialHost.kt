package com.singularityindonesia.serial

import android.hardware.usb.UsbDeviceConnection
import android.hardware.usb.UsbManager
import com.hoho.android.usbserial.driver.UsbSerialDriver
import com.hoho.android.usbserial.driver.UsbSerialPort
import com.hoho.android.usbserial.driver.UsbSerialProber

class SerialHost(
    private val usbManager: UsbManager?
) {
    fun getDevices(): List<UsbSerialDriver> {
        return UsbSerialProber
            .getDefaultProber()
            .findAllDrivers(usbManager)
            .orEmpty()
    }

    fun connect(driver: UsbSerialDriver): UsbSerialPort? {
        val connection: UsbDeviceConnection = usbManager?.openDevice(driver.device)
            ?: // add UsbManager.requestPermission(driver.getDevice(), ..) handling here
            return null

        val port = driver.ports[0] // Most devices have just one port (port 0)
        port.open(connection)
        port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE)

        return port
    }
}