/*
 * Copyright 2016 Bill Davidson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This class implements a dynamically sized fake map that doesn't do any real
 * mapping. Its purpose is to provide an ordered collection of key-value pairs
 * via the entrySet() method so that it can be used to build JSON objects using
 * the same code that builds them from real maps. It is meant to be faster than
 * doing normal put and iteration operations and to save memory compared to
 * normal maps. The gains tend to be effectively marginal but they are
 * measurable with objects that have large numbers of fields to be serialized.
 *
 * @author Bill Davidson
 */
class DynamicPseudoMap extends AbstractPseudoMap
{
    private ArrayList<Map.Entry<Object,Object>> entries;

    /**
     * Make a dynamically sized pseudo map
     */
    DynamicPseudoMap()
    {
        entries = new ArrayList<>();
    }

    /**
     * Create an entry for this key-value pair and add it to the list.
     *
     * @param key The key
     * @param value The value
     * @return null because this isn't a real map.
     */
    @Override
    public Object put( Object key, Object value )
    {
        entries.add(new Entry(key, value));
        return null;
    }

    /**
     * Get the entry set.
     *
     * @return The entry set.
     */
    @Override
    public Set<Map.Entry<Object,Object>> entrySet()
    {
        return new EntrySet();
    }

    /**
     * Get the number of entries in this pseudo map.
     *
     * @return the number of entries in this pseudo map.
     */
    @Override
    public int size()
    {
        return entries.size();
    }

    /**
     * Empty the list.
     */
    @Override
    public void clear()
    {
        entries.clear();
    }

    /**
     * Call the ArrayList's trimToSize() method.
     */
    void trimToSize()
    {
        entries.trimToSize();
    }

    /**
     * An entry set for this pseudo map.
     */
    private class EntrySet extends AbstractPseudoMap.EntrySet
    {
        @Override
        public Iterator<Map.Entry<Object,Object>> iterator()
        {
            return entries.iterator();
        }
    }
}
