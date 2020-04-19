package com.manulaiko.shinshinjiru.util;

import lombok.Data;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.ResolvableType;

/**
 * Mega application event multicaser.
 * ==================================
 *
 * Event multicaster that supports sync and async event casting.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Data
public class MegaApplicationEventMulticaster implements ApplicationEventMulticaster {
    private ApplicationEventMulticaster syncEventMulticaster;
    private ApplicationEventMulticaster asyncEventMulticaster;

    /**
     * @inheritDoc
     */
    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        // choose multicaster by annotation
        if (listener.getClass().getAnnotation(SyncEventListener.class) != null) {
            syncEventMulticaster.addApplicationListener(listener);
        } else {
            asyncEventMulticaster.addApplicationListener(listener);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addApplicationListenerBean(String listenerBeanName) {

    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        asyncEventMulticaster.removeApplicationListener(listener);
        syncEventMulticaster.removeApplicationListener(listener);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeApplicationListenerBean(String listenerBeanName) {

    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeAllListeners() {
        syncEventMulticaster.removeAllListeners();
        asyncEventMulticaster.removeAllListeners();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void multicastEvent(ApplicationEvent event) {
        asyncEventMulticaster.multicastEvent(event);
        syncEventMulticaster.multicastEvent(event);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void multicastEvent(ApplicationEvent event, ResolvableType eventType) {
        asyncEventMulticaster.multicastEvent(event, eventType);
        syncEventMulticaster.multicastEvent(event, eventType);
    }
}
