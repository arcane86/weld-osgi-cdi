package org.osgi.cdi.extension.impl;

import org.osgi.cdi.extension.api.annotation.Filter;

import javax.enterprise.util.AnnotationLiteral;

/**
 *
 * @author Mathieu ANCELIN - SERLI (mathieu.ancelin@serli.com)
 */
public class OSGiFilterQualifierType 
        extends AnnotationLiteral<Filter>
        implements Filter {

    private final String value;

    protected OSGiFilterQualifierType(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }
}
