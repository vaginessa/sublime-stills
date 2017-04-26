/*
 * Copyright {2017} {Aashrey Kamal Sharma}
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.aashreys.walls.domain.share;

import com.aashreys.walls.application.helpers.ImageDownloader;
import com.aashreys.walls.application.helpers.UiHandler;
import com.aashreys.walls.domain.device.DeviceResolution;
import com.aashreys.walls.domain.share.actions.ShareActionFactory;

import javax.inject.Inject;

/**
 * Created by aashreys on 09/02/17.
 */

public class ShareDelegateFactory {

    private final DeviceResolution deviceResolution;

    private final ShareActionFactory shareActionFactory;

    private final UiHandler.Factory uiHandlerFactory;

    private final ImageDownloader imageDownloader;

    @Inject
    public ShareDelegateFactory(
            DeviceResolution deviceResolution,
            ShareActionFactory shareActionFactory,
            UiHandler.Factory uiHandlerFactory,
            ImageDownloader imageDownloader
    ) {
        this.deviceResolution = deviceResolution;
        this.shareActionFactory = shareActionFactory;
        this.uiHandlerFactory = uiHandlerFactory;
        this.imageDownloader = imageDownloader;
    }

    public ShareDelegate create(ShareDelegate.Mode mode) {
        switch (mode) {
            case LINK:
                return new ShareImageLinkDelegate(shareActionFactory.createShareImageLinkAction());
            case COPY_LINK:
                return new CopyLinkDelegate(shareActionFactory.createCopyLinkAction());
            case PHOTO:
                return new ShareImageDelegate(
                        deviceResolution,
                        shareActionFactory.createShareImageAction(),
                        uiHandlerFactory.create(),
                        imageDownloader
                );
            case SET_AS:
                return new SetAsDelegate(
                        deviceResolution,
                        shareActionFactory.createSetAsAction(),
                        uiHandlerFactory.create(),
                        imageDownloader
                );
        }
        throw new IllegalArgumentException("Unexpected share mode");
    }
}
