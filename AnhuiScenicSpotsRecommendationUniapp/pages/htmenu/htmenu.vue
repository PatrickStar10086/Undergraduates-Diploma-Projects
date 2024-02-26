<template>
	<view>
		<view class=""
			style="color: #FFFFFF;padding: 40px 0 0 0;text-align: center;position: relative;background-color: #e54847;">
			<view class="">
				<image src="../../static/logo.png"
					style="width: 70px;height: 70px;border-radius: 35px;display: inline-block;border: 2px solid #FFFFFF;">
				</image>
			</view>
			<view class="" style="clear: both;"></view>
			<view class="" style="padding: 20px 25px;color: #FFFFFF;font-weight: bold;z-index: 10;">
				{{user.name}}
			</view>
		</view>

		<view class="" style="margin-top: 5px;background-color: #FFFFFF;padding: 10px;color: RGB(97,97,97);">

			<uni-list v-if="identity == '用户'">
				<uni-list-item title="个人中心" @click="urlto('../userindex/userindex')" thumb="../../static/geren.png"></uni-list-item>
				<uni-list-item title="访问历史" @click="urlto('../historyview/historyview')" thumb="../../static/lishi.png"></uni-list-item>
				<uni-list-item title="退出系统" @click="exitsystem" thumb="../../static/tuichu.png"></uni-list-item>
			</uni-list>

		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				identity: '',
				user: {},
			}
		},
		methods: {
			urlto(url) {

				uni.navigateTo({
					url: url
				})
			},
			exitsystem() {
				uni.clearStorage();
				uni.navigateTo({
					url: '../login/login'
				})
			},
		},
		onLoad(e) {
			let _this = this;

			uni.getStorage({
				key: 'identity',
				success(res) {

					console.log(res.data)

					_this.identity = res.data;
				}
			})
		},
		onShow() {
			let _this = this;

			uni.getStorage({
				key: 'identity',
				success(res) {
					console.log(res.data)

					_this.identity = res.data;

					if (_this.identity) {
						uni.getStorage({
							key: 'userinfo',
							success(res) {
								console.log(res.data)
								_this.user = res.data;
							},
							fail(res) {
								console.log(res)
							}
						})
					}

				},
				fail(res) {
					uni.navigateTo({
						url: "../login/login"
					})
					console.log(res)
				}
			})
		}
	}
</script>

<style>
	page {
		background-color: RGB(231, 231, 233);
	}

	.an {
		text-align: center;
		padding: 5px;
	}
</style>
