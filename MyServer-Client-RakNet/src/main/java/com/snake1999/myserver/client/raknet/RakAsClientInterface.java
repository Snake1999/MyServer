package com.snake1999.myserver.client.raknet;

import com.snake1999.myserver.api.API;
import com.snake1999.myserver.client.ClientInterface;

import static com.snake1999.myserver.api.API.Definition.PLATFORM_NATIVE;
import static com.snake1999.myserver.api.API.Usage.BLEEDING;

@API(usage = BLEEDING, definition = PLATFORM_NATIVE)
public final class RakAsClientInterface implements ClientInterface{

}
