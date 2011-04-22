package com.sample.osgi.paint.square;

import com.sample.osgi.paint.api.Shape;
import com.sample.osgi.paint.api.ShapeProvider;
import org.osgi.cdi.extension.api.annotation.Publish;
import org.osgi.cdi.extension.api.annotation.Sent;
import org.osgi.cdi.extension.api.annotation.Specification;
import org.osgi.cdi.extension.api.event.InterBundleEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@Publish
@ApplicationScoped
public class SquareShapeProvider implements ShapeProvider {

    @Override
    public Shape getShape() {
        return new Square();
    }

    @Override
    public String getId() {
        return Square.class.getName();
    }

    public void listen(@Observes @Sent @Specification(String.class) InterBundleEvent message) {
        System.out.println("received : " + message.typed(String.class).get());
    }
}
