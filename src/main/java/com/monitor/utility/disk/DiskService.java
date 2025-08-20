package com.monitor.utility.disk;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monitor.utility.commons.exceptions.BusinessRuntimeException;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSFileStore;

@Service
@Slf4j
public class DiskService {
    private final HardwareAbstractionLayer hal;
    List<OSFileStore> fsList;

    public DiskService(){
        SystemInfo si = new SystemInfo();
        this.hal = si.getHardware();
        this.fsList = si.getOperatingSystem().getFileSystem().getFileStores();
    }
    
    public List<Disk> getDiskStats(){
        try {
            List<Disk> disks = fsList.stream().map(fs -> {
                Disk disk = new Disk();
                disk.setName(fs.getName());
                disk.setMountPoint(fs.getMount());
                disk.setFilesystemType(fs.getType());
                disk.setTotalSpace(fs.getTotalSpace());
                disk.setFreeSpace((fs.getFreeSpace()));
                disk.setUsedSpace(fs.getTotalSpace() - fs.getFreeSpace());
                disk.setUsagePercent(100.0 * (fs.getTotalSpace() - fs.getFreeSpace()));
                return disk;
            }).toList();

            List<HWDiskStore> hwDiskStores = hal.getDiskStores();
            for (HWDiskStore disk : hwDiskStores) {
                disk.updateAttributes();
                disks.forEach(d -> {
                    if(d.getName().equals(disk.getName())){
                        d.setReadBytes(disk.getReadBytes());
                        d.setWriteBytes(disk.getWriteBytes());
                        d.setReadCount(disk.getReads());
                        d.setWriteCount(d.getWriteCount());
                    }
                });
            }

            return disks;
        } catch (Exception e) {
            log.error("Error Retrieving Disk Stats {}", e.getMessage());
            throw new BusinessRuntimeException("Failed to retrieve Disk Stats");
        }
    }
}
