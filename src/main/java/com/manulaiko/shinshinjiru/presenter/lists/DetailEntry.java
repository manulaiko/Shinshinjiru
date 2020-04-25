package com.manulaiko.shinshinjiru.presenter.lists;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Detail entry.
 * =============
 *
 * Proxy bean to get the TableEntry instance to show in the Details window.
 *
 * @author Manulaiko
 */
@Component
@Data
public class DetailEntry {
    private TableEntry entry;
}
