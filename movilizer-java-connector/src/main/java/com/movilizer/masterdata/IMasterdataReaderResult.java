package com.movilizer.masterdata;

import com.movilitas.movilizer.v11.MovilizerMasterdataPoolUpdate;

import java.util.List;

/**
 * @author Peter.Grigoriev@movilizer.com
 */
public interface IMasterdataReaderResult {
    MovilizerMasterdataPoolUpdate getMasterdataPoolUpdate();

    List<String> getErrorEventIds();
}