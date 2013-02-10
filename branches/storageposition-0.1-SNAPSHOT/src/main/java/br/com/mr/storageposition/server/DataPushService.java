package br.com.mr.storageposition.server;

import java.io.Serializable;

public interface DataPushService<T extends Serializable> {

	void send(T object);
}
