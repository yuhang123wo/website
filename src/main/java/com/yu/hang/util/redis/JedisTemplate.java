/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.yu.hang.util.redis;

import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.Pool;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * JedisTemplate 提供了一个template方法，负责对Jedis连接的获取与归还。
 * JedisAction<T> 和 JedisActionNoResult两种回调接口，适用于有无返回值两种情况。
 * 同时提供一些最常用函数的封装, 如get/set/zadd等。
 */
public class JedisTemplate {
	private static Logger logger = LoggerFactory.getLogger(JedisTemplate.class);

	private Pool<Jedis> jedisPool;

	public JedisTemplate(Pool<Jedis> jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**
	 * 执行有返回结果的action。
	 */
	public <T> T execute(JedisAction<T> jedisAction) throws JedisException {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = jedisPool.getResource();
			return jedisAction.action(jedis);
		} catch (JedisConnectionException e) {
			logger.error("Redis connection lost.", e);
			broken = true;
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	/**
	 * 执行无返回结果的action。
	 */
	public void execute(JedisActionNoResult jedisAction) throws JedisException {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = jedisPool.getResource();
			jedisAction.action(jedis);
		} catch (JedisConnectionException e) {
			logger.error("Redis connection lost.", e);
			broken = true;
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	/**
	 * 根据连接是否已中断的标志，分别调用returnBrokenResource或returnResource。
	 */
	protected void closeResource(Jedis jedis, boolean connectionBroken) {
		if (jedis != null) {
			try {
				if (connectionBroken) {
					jedisPool.returnBrokenResource(jedis);
				} else {
					jedisPool.returnResource(jedis);
				}
			} catch (Exception e) {
				logger.error("Error happen when return jedis to pool, try to close it directly.", e);
				JedisUtils.closeJedis(jedis);
			}
		}
	}

	/**
	 * 获取内部的pool做进一步的动作。
	 */
	public Pool<Jedis> getJedisPool() {
		return jedisPool;
	}

	/**
	 * 有返回结果的回调接口定义。
	 */
	public interface JedisAction<T> {
		T action(Jedis jedis);
	}

	/**
	 * 无返回结果的回调接口定义。
	 */
	public interface JedisActionNoResult {
		void action(Jedis jedis);
	}

	// ////////////// 常用方法的封装 ///////////////////////// //

	// ////////////// 公共 ///////////////////////////
	/**
	 * 删除key, 如果key存在返回true, 否则返回false。
	 */
	public Boolean del(final String... keys) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.del(keys) == 1 ? true : false;
			}
		});
	}

	public void flushDB() {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.flushDB();
			}
		});
	}

	// ////////////// 关于String ///////////////////////////
	/**
	 * 如果key不存在, 返回null.
	 */
	public String get(final String key) {
		return execute(new JedisAction<String>() {

			@Override
			public String action(Jedis jedis) {
				return jedis.get(key);
			}
		});
	}

	/**
	 * 如果key不存在, 返回null.
	 */
	public Long getAsLong(final String key) {
		String result = get(key);
		return result != null ? Long.valueOf(result) : null;
	}

	/**
	 * 如果key不存在, 返回null.
	 */
	public Integer getAsInt(final String key) {
		String result = get(key);
		return result != null ? Integer.valueOf(result) : null;
	}

	public void set(final String key, final String value) {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.set(key, value);
			}
		});
	}

	public void setex(final String key, final String value, final int seconds) {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.setex(key, seconds, value);
			}
		});
	}

	/**
	 * 如果key还不存在则进行设置，返回true，否则返回false.
	 */
	public Boolean setnx(final String key, final String value) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.setnx(key, value) == 1 ? true : false;
			}
		});
	}

	/**
	 * 综合setNX与setEx的效果。
	 */
	public Boolean setnxex(final String key, final String value, final int seconds) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				String result = jedis.set(key, value, "NX", "EX", seconds);
				return JedisUtils.isStatusOk(result);
			}
		});
	}

	public Long incr(final String key) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.incr(key);
			}
		});
	}

    public Long incrBy(final String key,final long value) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.incrBy(key,value);
            }
        });
    }

	public Long decr(final String key) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.decr(key);
			}
		});
	}

	// ////////////// 关于List ///////////////////////////
	public void lpush(final String key, final String... values) {
		execute(new JedisActionNoResult() {
			@Override
			public void action(Jedis jedis) {
				jedis.lpush(key, values);
			}
		});
	}

	public String rpop(final String key) {
		return execute(new JedisAction<String>() {

			@Override
			public String action(Jedis jedis) {
				return jedis.rpop(key);
			}
		});
	}
	
	/**
	 * 返回list所有值
	 * @param key
	 * @return
	 */
	public List<String> lrange(final String key) {
		return execute(new JedisAction<List<String>>() {

			@Override
			public List<String> action(Jedis jedis) {
				return jedis.lrange(key, 0, -1);
			}
		});
	}

	/**
	 * 返回指定索引的list值
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> lrange(final String key, final long start, final long end) {
		return execute(new JedisAction<List<String>>() {
			
			@Override
			public List<String> action(Jedis jedis) {
				return jedis.lrange(key, start, end);
			}
		});
	}

	/**
	 * 返回List长度, key不存在时返回0，key类型不是list时抛出异常.
	 */
	public Long llen(final String key) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	/**
	 * 删除List中的第一个等于value的元素，value不存在或key不存在时返回false.
	 */
	public Boolean lremOne(final String key, final String value) {
		return execute(new JedisAction<Boolean>() {
			@Override
			public Boolean action(Jedis jedis) {
				Long count = jedis.lrem(key, 1, value);
				return (count == 1);
			}
		});
	}

	/**
	 * 删除List中的所有等于value的元素，value不存在或key不存在时返回false.
	 */
	public Boolean lremAll(final String key, final String value) {
		return execute(new JedisAction<Boolean>() {
			@Override
			public Boolean action(Jedis jedis) {
				Long count = jedis.lrem(key, 0, value);
				return (count > 0);
			}
		});
	}

	// ////////////// 关于Sorted Set ///////////////////////////
	/**
	 * 加入Sorted set, 如果member在Set里已存在, 只更新score并返回false, 否则返回true.
	 */
	public Boolean zadd(final String key, final String member, final double score) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.zadd(key, score, member) == 1 ? true : false;
			}
		});
	}

    public Double zincrby(final String key, final String member, final double score) {
        return execute(new JedisAction<Double>() {

            @Override
            public Double action(Jedis jedis) {
                return jedis.zincrby(key,score,member);
            }
        });
    }

	/**
	 * 删除sorted set中的元素，成功删除返回true，key或member不存在返回false。
	 */
	public Boolean zrem(final String key, final String member) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.zrem(key, member) == 1 ? true : false;
			}
		});
	}

	/**
	 * 当key不存在时返回null.
	 */
	public Double zscore(final String key, final String member) {
		return execute(new JedisAction<Double>() {

			@Override
			public Double action(Jedis jedis) {
				return jedis.zscore(key, member);
			}
		});
	}

	/**
	 * 返回sorted set长度, key不存在时返回0.
	 */
	public Long zcard(final String key) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.zcard(key);
			}
		});
	}
    public ScanResult<Tuple> zscan(final String key, final String cursor) {
        return execute(new JedisAction<ScanResult<Tuple>>() {
            @Override
            public ScanResult<Tuple> action(Jedis jedis) {
                return jedis.zscan(key, cursor);
            }
        });
    }
    public ScanResult<Tuple> zscan(final String key, final String cursor,final ScanParams params) {
        return execute(new JedisAction<ScanResult<Tuple>>() {
            @Override
            public ScanResult<Tuple> action(Jedis jedis) {
                return jedis.zscan(key, cursor,params);
            }
        });
    }

    public Long hset(final String key,  final String field, final String value) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.hset(key, field, value);
            }
        });
    }
    
    /**
	 * 如果key不存在, 返回null.
	 */
	public String hget(final String key, final String field) {
		return execute(new JedisAction<String>() {

			@Override
			public String action(Jedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}
    public Long hdel(final String key, final String field) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.hdel(key, field);
            }
        });
    }
    public Map<String,String> hgetAll(final String key) {
        return execute(new JedisAction<Map<String,String>>() {

            @Override
            public Map<String,String> action(Jedis jedis) {
                return jedis.hgetAll(key);
            }
        });
    }

    public void expire(final String key,final int expire){
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
               int ttl = jedis.ttl(key).intValue();
                if(ttl>0)
                    jedis.expire(key,ttl+expire);
            }
        });
    }
    
    public Long ttl(final String key) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.ttl(key);
            }
        });
    }

    public Set<String> keys(final String key) {
        return execute(new JedisAction<Set<String>>() {
            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.keys(key);
            }
        });
    }

    public Long sadd(final String key,  final String value) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.sadd(key, value);
            }
        });
    }
    
    public Set<String> sinter(final String key) {
        return execute(new JedisAction<Set<String>>() {

            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.sinter(key);
            }
        });
    }
    
    public Boolean sismember(final String key,  final String member) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                return jedis.sismember(key, member);
            }
        });
    }
    public Long srem(final String key,  final String value) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.srem(key, value);
            }
        });
    }
    public Set<String> smembers(final String key) {
        return execute(new JedisAction<Set<String>>() {

            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.smembers(key);
            }
        });
    }    
    public Boolean exists(final String key) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                return jedis.exists(key);
            }
        });
    }
    public Long dbSize() {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.dbSize();
            }
        });
    }
	public byte[] get(final byte[] key) {
		return execute(new JedisAction<byte[]>() {

			@Override
			public byte[] action(Jedis jedis) {
				return jedis.get(key);
			}
		});
	}
	public Boolean del(final byte[]... keys) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.del(keys) == 1 ? true : false;
			}
		});
	}
	public void set(final byte[] key, final byte[] value) {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.set(key, value);
			}
		});
	}
    public Set<byte[]> getKeys(final String key) {
        return execute(new JedisAction<Set<byte[]>>() {
            @Override
            public Set<byte[]> action(Jedis jedis) {
                return jedis.keys(key.getBytes());
            }
        });
    }
    public void setex(final byte[] key, final byte[] value, final int seconds) {
		execute(new JedisActionNoResult() {
			@Override
			public void action(Jedis jedis) {
				jedis.setex(key, seconds, value);
			}
		});
	}

	/**
	 * 根据key获取对象
	 */
	public <T extends Serializable> T getObj(final String key) {
		return execute(new JedisAction<T>() {
			@Override
			public T action(Jedis jedis) {
				byte[] ret = jedis.get(key.getBytes());
				if(ret!=null)
					return (T) SerializationUtils.deserialize(ret);
				return null;
			}
		});
	}

	/**
	 * 设置对象
	 * @param key
	 * @param value
	 * @param expireSeconds 过期时间，大于0有效
	 */
	public void setObj(final String key,final Serializable value,final int expireSeconds) {
		execute(new JedisActionNoResult() {
			@Override
			public void action(Jedis jedis) {
				byte[] obj = SerializationUtils.serialize(value);
				jedis.set(key.getBytes(),obj);
				if(expireSeconds>0)
					jedis.expire(key,expireSeconds);
			}
		});


	}

}
