/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.modules.mskj.repository;

import me.zhengjie.modules.mskj.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Fu Ding
 * @date 2020-10-30
 **/
public interface DeviceRepository extends JpaRepository<Device, String>, JpaSpecificationExecutor<Device> {
    /**
     * 根据deviceID更新charging_pile_status
     *
     * @param deviceId /
     */
    @Modifying
    @Query(value = " update sys_device set charging_pile_status = $2 where device_id = ?1 and charging_pile_status = $3", nativeQuery = true)
    int updateChargingPileStatus(String deviceId, Integer toStatus, Integer fromStatus);
}