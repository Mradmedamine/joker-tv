package org.joker.tv.service;

import org.joker.tv.model.front.web.ActivationResult;
import org.joker.tv.model.front.web.DeviceDto;

public interface ActivationService {

	ActivationResult activateDevice(DeviceDto device);

}
