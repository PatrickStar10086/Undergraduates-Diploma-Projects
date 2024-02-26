<template>

	<view style="padding: 20px;">
		
		<view style="text-align: center;font-size: 20px;font-weight: bold;margin-bottom: 20px;">
			{{scenicspot.name}}
		</view>
		
		<view style="text-indent: 2em;">
			{{scenicspot.introduce}}
		</view>
		
		<view style="margin-top: 10px;word-break : break-all;">
			原文链接：{{scenicspot.orgurl}}
		</view>
		
		<view style="text-align: center;margin-top: 30px;">
			<image src="../../static/weishoucang.png" v-if="shoucangnum == 0" @click="shoucang" class="icon"></image>
			<image src="../../static/yishoucang.png" v-if="shoucangnum > 0" @click="quxiaoshoucang" class="icon"></image>
		</view>
		
		<view style="height: 100px;"></view>

	</view>

</template>

<script>
	export default {
		data() {
			return {
				scenicspot: {},
				shoucangnum:0,
				userid:0,
			}
		},
		methods: {
			shoucang(){
				let _this = this
				if(this.userid == 0){
					uni.showToast({
						title:'请先登录',
						icon:'none'
					})
					return;
				}
				uni.request({
					url: "http://localhost:8080/ahjdtj/shoucang.action?scenicspotid=" + _this.scenicspot['id'] + "&userid=" + _this.userid,
					method: "POST",
					data: '',
					success(res) {
						console.log(res)
				
						uni.showToast({
							icon: "none",
							title: "收藏成功"
						})
						
						_this.souusuoshoucang();
					}
				})
			},
			quxiaoshoucang(){
				let _this = this
				if(this.userid == 0){
					uni.showToast({
						title:'请先登录',
						icon:'none'
					})
					return;
				}
				uni.request({
					url: "http://localhost:8080/ahjdtj/quxiaoshoucang.action?scenicspotid=" + _this.scenicspot['id'] + "&userid=" + _this.userid,
					method: "POST",
					data: '',
					success(res) {
						console.log(res)
				
						uni.showToast({
							icon: "none",
							title: "取消成功"
						})
						
						_this.souusuoshoucang();
					}
				})
			},
			updatescenicspotactjson(e) {
				let _this = this
				uni.request({
					url: "http://localhost:8080/ahjdtj/updatescenicspotactjson.action",
					method: "POST",
					data: JSON.stringify(e.detail.value),
					success(res) {
						console.log(res)

						uni.showToast({
							icon: "none",
							title: "修改景点成功"
						})
					}
				})
			},
			souusuoshoucang(){
				let _this = this
				uni.request({
					url: "http://localhost:8080/ahjdtj/shoucangshu.action?scenicspotid=" + _this.scenicspot['id'] + "&userid=" + _this.userid,
					method: "POST",
					data: '',
					success(res) {
						console.log(res)
				
						_this.shoucangnum = res.data;
						
						console.log(res)
						console.log(_this.shoucangnum)
					}
				})
			}


		},
		onLoad(e) {
			
			let _this = this;
			
			_this.scenicspot['id'] = e.id;
			
			uni.request({
				url: "http://localhost:8080/ahjdtj/scenicspotdetailsjson.action?id=" + e.id,
				success: res => {
					console.log(res.data)
					_this.scenicspot = res.data.scenicspot;
				}
			})
			
			uni.getStorage({
				key: 'id',
				success(res) {
					console.log(res);
					_this.userid = res.data;
			
					// uni.request({
					// 	url: "http://localhost:8080/ahjdtj/fangwenlishi.action?scenicspotid=" + e.id + "&userid=" + res.data,
					// 	success: res => {
							
					// 	}
					// })
					_this.souusuoshoucang();
				},
				fail(res) {
					_this.userid = 0;
				}
			});
			
			
			
		},
	}
</script>

<style>
	
	.icon{
		width: 50px;
		height: 50px;
	}
	
	page {
		background-color: #f8f8f8;
	}

	.input {
		border-bottom: 1px solid #d6d6d6;
		padding: 10px;
		margin: 5px 10px;
	}

	.form {
		background-color: #FFFFFF;
		margin-top: 15px;
		padding: 0 0px 15px 0px;
	}

	.biaodanbtn {
		color: #FFFFFF;
		background-color: #df2d2d;
		margin: 15px;
	}

	.kefu {
		color: #df2d2d;
		text-align: center;
		font-size: 14px;
		margin-top: 10px;
	}
</style>
