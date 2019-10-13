package com.athleticspot.training.infrastracture.dto.in;

import com.athleticspot.training.domain.trainingsurvey.BaseInformation;
import org.springframework.stereotype.Component;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class BaseInformationInDtoAssembler {

    public BaseInformation assemble(BaseInformationInDto baseInformationInDto) {
        return new BaseInformation(baseInformationInDto.getBirthDay(),
            baseInformationInDto.getBodyMass(),
            baseInformationInDto.getHeight(),
            baseInformationInDto.getMetricSystemType());
    }
}
