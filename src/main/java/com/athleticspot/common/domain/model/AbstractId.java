//   Copyright 2012,2013 Vaughn Vernon
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package com.athleticspot.common.domain.model;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@MappedSuperclass
public abstract class AbstractId
    implements Identity, Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    private String uuid;

    public String uuid() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            AbstractId typedObject = (AbstractId) anObject;
            equalObjects = this.uuid().equals(typedObject.uuid());
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
            +(this.hashOddValue() * this.hashPrimeValue())
                + this.uuid().hashCode();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [uuid=" + uuid + "]";
    }

    protected AbstractId(String anId) {
        this();

        this.setUuid(anId);
    }

    protected AbstractId() {
        super();
    }

    protected abstract int hashOddValue();

    protected abstract int hashPrimeValue();

    protected void validateId(String anId) {
        // implemented by subclasses for validation.
        // throws a runtime exception if invalid.
    }

    private void setUuid(String anId) {
        Assert.hasLength(anId, "The basic identity is required.");
        Assert.isTrue(anId.length() == 36, "The basic identity must be 36 characters.");
        this.validateId(anId);
        this.uuid = anId;
    }
}
