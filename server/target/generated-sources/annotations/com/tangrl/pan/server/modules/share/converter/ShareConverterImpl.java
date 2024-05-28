package com.tangrl.pan.server.modules.share.converter;

import com.tangrl.pan.server.modules.share.context.CreateShareUrlContext;
import com.tangrl.pan.server.modules.share.po.CreateShareUrlPO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-28T21:08:15+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class ShareConverterImpl implements ShareConverter {

    @Override
    public CreateShareUrlContext createShareUrlPO2CreateShareUrlContext(CreateShareUrlPO createShareUrlPO) {
        if ( createShareUrlPO == null ) {
            return null;
        }

        CreateShareUrlContext createShareUrlContext = new CreateShareUrlContext();

        createShareUrlContext.setShareName( createShareUrlPO.getShareName() );
        createShareUrlContext.setShareType( createShareUrlPO.getShareType() );
        createShareUrlContext.setShareDayType( createShareUrlPO.getShareDayType() );

        createShareUrlContext.setUserId( com.tangrl.pan.server.common.utils.UserIdUtil.get() );

        return createShareUrlContext;
    }
}
