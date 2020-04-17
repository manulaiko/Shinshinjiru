package com.manulaiko.shinshinjiru.api;

import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * API Service.
 * ============
 *
 * Contains the API implementation.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Service
@Data
public class APIService {
    private APIToken token;
}
