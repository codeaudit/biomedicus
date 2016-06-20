/*
 * Copyright (c) 2016 Regents of the University of Minnesota.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.umn.biomedicus.common.labels;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

class LimitedLabels<T> extends AbstractLabels<T> {
    private final Labels<T> labels;

    private final int limit;

    LimitedLabels(Labels<T> labels, int limit) {
        this.labels = labels;
        this.limit = limit;
    }

    @Override
    public Labels<T> limit(int max) {
        if (max >= limit) {
            return this;
        }
        return new LimitedLabels<>(labels, max);
    }

    @Override
    public Iterator<Label<T>> iterator() {
        Iterator<Label<T>> iterator = labels.iterator();
        return new Iterator<Label<T>>() {
            private int count = 0;
            @Override
            public boolean hasNext() {
                return count < limit && iterator.hasNext();
            }

            @Override
            public Label<T> next() {
                if (count >= limit) {
                    throw new NoSuchElementException();
                }
                count += 1;
                return iterator.next();
            }
        };
    }

    @Override
    public Stream<Label<T>> stream() {
        return labels.stream().limit(limit);
    }
}