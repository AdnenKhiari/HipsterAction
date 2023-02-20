package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ActeurDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActeurDTO.class);
        ActeurDTO acteurDTO1 = new ActeurDTO();
        acteurDTO1.setId(1L);
        ActeurDTO acteurDTO2 = new ActeurDTO();
        assertThat(acteurDTO1).isNotEqualTo(acteurDTO2);
        acteurDTO2.setId(acteurDTO1.getId());
        assertThat(acteurDTO1).isEqualTo(acteurDTO2);
        acteurDTO2.setId(2L);
        assertThat(acteurDTO1).isNotEqualTo(acteurDTO2);
        acteurDTO1.setId(null);
        assertThat(acteurDTO1).isNotEqualTo(acteurDTO2);
    }
}
