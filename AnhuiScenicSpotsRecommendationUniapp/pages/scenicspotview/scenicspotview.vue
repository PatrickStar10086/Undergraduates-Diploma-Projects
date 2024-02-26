<template>
	<view class="container">

		<uni-search-bar @input="changesearch" v-model="search" placeholder="请输入搜索内容" />


		<view class="liebiao">

			<view class="liebiao-item" v-for="item in scenicspotall" :key="item.id">
				<view class="liebiao-item-com2">
					<view class="title">{{item.name}}</view>
					<view class="xiaowenzi">{{item.introduce}}</view>
				</view>
				<view class="liebiao-item-com3">
					<view class="btn" :data-scenicspotid="item.id" @click="scenicspotxiangqing">详情</view>
				</view>
			</view>

		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				"scenicspotall": [],
				"scenicspotalltemp": [],
				"search": "",
			}
		},
		methods: {
			changesearch(event) {
				//this.search = event.value;
				let search = event.value;
				this.scenicspotall = JSON.parse(JSON.stringify(this.scenicspotalltemp));


				for (let i = this.scenicspotall.length - 1; i >= 0; i--) {

					if (JSON.stringify(this.scenicspotall[i]).indexOf(search) == -1 && search) {
						this.scenicspotall.splice(i, 1)
					}
				}


			},
			scenicspotxiangqing(e) {
				uni.navigateTo({
					url: "../scenicspotxiangqing/scenicspotxiangqing?id=" + e.currentTarget.dataset.scenicspotid
				})
			},
			updatescenicspot(e) {
				uni.navigateTo({
					url: "../updatescenicspot/updatescenicspot?id=" + e.currentTarget.dataset.scenicspotid
				})
			},
			deletescenicspot(e) {

				let _this = this;
				console.log(e);

				uni.request({
					url: "http://localhost:8080/ahjdtj/deletescenicspotjson.action?id=" + e.currentTarget.dataset
						.scenicspotid,
					success: res => {

						for (let i = _this.scenicspotalltemp.length - 1; i >= 0; i--) {
							if (_this.scenicspotalltemp[i].id == e.currentTarget.dataset.scenicspotid) {
								_this.scenicspotalltemp.splice(i, 1)
							}
						}

						uni.showToast({
							title: '删除景点成功',
							icon: 'none',
							duration: 1000
						})

					}
				})
			},
		},
		onShow() {
			
		},
		onLoad(e) {
			
			let _this = this;
			
			uni.getStorage({
				key: 'id',
				success(res) {
					console.log(res);
					_this.id = res.data;
			
					uni.request({
						url: "http://localhost:8080/ahjdtj/cainixihuanjson.action?userID=" + res.data,
						success: res => {
							console.log(res.data)
							_this.scenicspotall = res.data.scenicspotall;
							_this.scenicspotalltemp = res.data.scenicspotall;
						}
					})
			
				}
			});
			
		},
		onReachBottom() {
			uni.showToast({
				title: '到底了',
				icon: 'none',
				duration: 1000
			})
		},
	}
</script>

<style>
	page {
		background-color: #f5f5f5;
	}

	.liebiao .liebiao-item {
		border-bottom: 1px solid #e6e6e6;
		padding: 10px;
		height: 90px;
	}

	.liebiao .liebiao-item .liebiao-item-com2 {
		float: left;
		width: 65%;
		margin-left: 5%;
	}

	.liebiao .xiaowenzi {
		height: 69px;
		font-size: 13px;
		color: #666;
		overflow: hidden;
		text-overflow: ellipsis;
		
	}

	.liebiao .liebiao-item .liebiao-item-com3 {
		float: left;
		width: 25%;
		margin-left: 5%;
		display: grid;
		align-items: center;
		justify-content: center;
		height: 90px;
	}

	.liebiao .btn {
		width: 47px;
		height: 27px;
		line-height: 28px;
		text-align: center;
		box-sizing: border-box;
		background-color: #f03d37;
		color: #fff;
		border-radius: 4px;
		white-space: nowrap;
		font-size: 12px;
		cursor: pointer;
	}

	.liebiao .liebiao-item .liebiao-item-com1 .img {
		width: 100%;
		height: 90px;
	}

	.liebiao .liebiao-item .title {
		font-size: 17px;
		color: #333;
		font-weight: 700;
		padding-right: 5px;
		flex-shrink: 1;
	}

	.liebiao .pingfen {
		font-weight: 700;
		color: #faaf00;
		font-size: 15px;
		display: inline-block;
	}
</style>
