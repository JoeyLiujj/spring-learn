package com.taikang.common.redisUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.taikang.common.redisUtil.routing.RoutingSecooRedis;
import com.taikang.common.redisUtil.shard.ShardSecooRedis;
import com.taikang.dbrouting.RoutingException;
import com.taikang.utils.ConsistentHash;
/**
 * 应用下的集群策略
 * <P>File name : AppRedis.java </P>
 * <P>Author : zouzhihua </P> 
 * <P>Date : 2013-4-14 </P>
 */
public class AppRedis {
	
	private String dispatch;
	private String clusterStrategy;
	private ConsistentHash<ISecooRedis> hash;
	private List<ISecooRedis> secooRedisList = new ArrayList<ISecooRedis>();
	public AppRedis(String appId,String dispatch,String clusterStrategy, List<String> clusterIds){
		this.dispatch = dispatch;
		this.clusterStrategy = clusterStrategy;
		for(String clusterId : clusterIds){
			ISecooRedis redis = null;
			if("ROUTING".equals(dispatch)){
				redis = new RoutingSecooRedis(clusterId);
			}else if("SHARD".equals(dispatch)){
				redis = new ShardSecooRedis(clusterId);
			}
			secooRedisList.add(redis);
		}

	}
	/**
	 * 获取集群策略中的一组服务
	 * AppRedis.getLafasoRedis()<BR>
	 * <P>Author : zouzhihua </P>  
	 * <P>Date : 2013-4-14 </P>
	 * @param token
	 * @return
	 */
	public ISecooRedis getSecooRedis(String token){
		if("ROUTING".equals(dispatch)){
			ISecooRedis redis = null;
			if("HASH".equals(clusterStrategy)){
				if (hash == null) {// 初始化一致性Hash算法，只初始化一次
					hash = new ConsistentHash<ISecooRedis>(secooRedisList);
				}
				redis = hash.get(token);
			}else if("ONLY".equals(clusterStrategy)){
				redis = secooRedisList.get(0);
			}
			return redis;
		}else if("SHARD".equals(dispatch)){
			ISecooRedis redis = secooRedisList.get(0);
			return redis;
		}else{
			throw new RoutingException(
					"the dispatch:"
							+ this.dispatch
							+ " can not support,please set the  dispatch in appredis.xml correct");
		}
	}
	/**
	 * 获取当前应用下的所有服务
	 * AppRedis.getAllRedisCluster()<BR>
	 * <P>Author : zouzhihua </P>  
	 * <P>Date : 2013-4-14 </P>
	 * @return
	 */
	public List<ISecooRedis> getAllSecooRedis(){
		return Collections.unmodifiableList(secooRedisList);
	}
	/**
	 * AppRedisCluster.main()<BR>
	 * <P>Author : zouzhihua </P>  
	 * <P>Date : 2013-1-28 </P>
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
