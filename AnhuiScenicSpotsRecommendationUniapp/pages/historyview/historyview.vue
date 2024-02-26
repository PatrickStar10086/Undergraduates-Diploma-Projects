<template>
	<view class="container">

		<uni-search-bar @input="changesearch" v-model="search" placeholder="请输入搜索内容" />


		<view class="liebiao">

			<view class="liebiao-item" v-for="item in historyall" :key="item.id">
				<view class="liebiao-item-com2">
					<view class="title">{{item.scenicspot}}</view>
					<view class="xiaowenzi">{{item.addtime}}</view>
				</view>
				<view class="liebiao-item-com3">
					<view class="btn" :data-historyid="item.id" @click="deletehistory">取消</view>
				</view>
			</view>

		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				"historyall": [],
				"historyalltemp": [],
				"search": "",
			}
		},
		methods: {
			changesearch(event) {
				//this.search = event.value;
				let search = event.value;
				this.historyall = JSON.parse(JSON.stringify(this.historyalltemp));


				for (let i = this.historyall.length - 1; i >= 0; i--) {

					if (JSON.stringify(this.historyall[i]).indexOf(search) == -1 && search) {
						this.historyall.splice(i, 1)
					}
				}


			},
			deletehistory(e) {
			
				let _this = this;
				console.log(e);
			
				uni.request({
					url: "http://localhost:8080/ahjdtj/deletehistoryjson.action?id=" + e.currentTarget.dataset
						.historyid,
					success: res => {
			
						for (let i = _this.historyalltemp.length - 1; i >= 0; i--) {
							if (_this.historyalltemp[i].id == e.currentTarget.dataset.historyid) {
								_this.historyalltemp.splice(i, 1)
							}
						}
			
						uni.showToast({
							title: '取消收藏成功',
							icon: 'none',
							duration: 1000
						})
			
					}
				})
			},
		},
		onLoad(e) {

			let _this = this;
			uni.request({
				url: "http://localhost:8080/ahjdtj/searchhistoryjson.action",
				success: res => {
					console.log(res.data)
					_this.historyall = res.data.historyall;
					_this.historyalltemp = res.data.historyall;
				}
			})
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
		height: 40px;
	}

	.liebiao .liebiao-item .liebiao-item-com2 {
		float: left;
		width: 65%;
		margin-left: 5%;
	}
	
	.liebiao .xiaowenzi {
		height: 40px;
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
		height: 40px;
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
