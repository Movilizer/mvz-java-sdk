package com.movilizer.pull;

import com.movilitas.movilizer.v14.MovilizerResponse;

/**
 * @author Peter.Grigoriev@movilizer.com
 */
public interface IMovilizerResponseObserver {
    void onResponseAvailable(MovilizerResponse response) throws KeepItOnTheCloudException;
}
