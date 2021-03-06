package org.ff4j.services

import org.apache.commons.lang3.StringUtils
import org.ff4j.FF4j
import org.ff4j.cache.FF4jCacheProxy
import org.ff4j.core.FeatureStore
import org.ff4j.services.domain.CacheApiBean
import org.ff4j.services.domain.FeatureApiBean
import org.ff4j.services.domain.FeatureStoreApiBean
import org.ff4j.services.domain.GroupDescApiBean
import org.ff4j.services.exceptions.FeatureStoreNotCached
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import java.util.stream.Collectors

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
@Service
class FeatureStoreServices(@Autowired val fF4j: FF4j) {
    fun getFeatureStore(): FeatureStoreApiBean {
        return FeatureStoreApiBean(this.fF4j.featureStore)
    }

    fun getAllFeatures(): Collection<FeatureApiBean> {
        val allFeatures = fF4j.featureStore.readAll()
        return if (CollectionUtils.isEmpty(allFeatures)) {
            ArrayList(0)
        } else {
            val features = ArrayList<FeatureApiBean>(allFeatures.size)
            features.addAll(allFeatures.values.stream().map { it -> FeatureApiBean(it) }.collect(Collectors.toList()))
            features
        }
    }

    fun getAllGroups(): MutableCollection<GroupDescApiBean> {
        val groups = HashMap<String, GroupDescApiBean>()
        val allFeatures = fF4j.featureStore.readAll()
        allFeatures?.let {
            allFeatures.values.forEach {
                initGroup(groups, it.uid, it.group)
            }
        }
        return groups.values
    }

    private fun initGroup(groups: HashMap<String, GroupDescApiBean>, uid: String?, groupName: String?) {
        groupName?.let {
            if (StringUtils.isNotBlank(groupName)) {
                if (!groups.containsKey(groupName)) {
                    groups[groupName] = GroupDescApiBean(groupName, ArrayList())
                }
                groups[groupName]?.features?.add(uid!!)
            }
        }
    }

    fun deleteAllFeatures() {
        fF4j.featureStore.clear()
    }

    fun getFeaturesFromCache(): CacheApiBean {
        return if (fF4j.featureStore is FF4jCacheProxy) {
            CacheApiBean(fF4j.featureStore as FeatureStore)
        } else {
            throw FeatureStoreNotCached()
        }
    }

    fun clearCachedFeatureStore() {
        if (fF4j.featureStore is FF4jCacheProxy) {
            (fF4j.featureStore as FF4jCacheProxy).cacheManager.clearFeatures()
        } else {
            throw FeatureStoreNotCached()
        }
    }
}