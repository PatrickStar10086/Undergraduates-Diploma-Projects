<template>
	<view>
		
		<uni-drawer :visible="showLeft" mode="left" @close="closeDrawer('left')">
			<scroll-view scroll-y="true" :style="scviesty">
			
			<!-- #ifndef MP-BAIDU || MP-ALIPAY || MP-TOUTIAO -->
			<uni-list v-if="identity == '管理员'">
				<uni-list-item title="个人中心" @click="urlto('../../pages/adminindex/adminindex')" />				<uni-list-item title="添加管理员" @click="urlto('../../pages/addadmin/addadmin')" />				<uni-list-item title="管理员管理" @click="urlto('../../pages/adminmanage/adminmanage')" />				<uni-list-item title="添加景点" @click="urlto('../../pages/addscenicspot/addscenicspot')" />				<uni-list-item title="景点管理" @click="urlto('../../pages/scenicspotmanage/scenicspotmanage')" />				<uni-list-item title="添加访问历史" @click="urlto('../../pages/addhistory/addhistory')" />				<uni-list-item title="访问历史管理" @click="urlto('../../pages/historymanage/historymanage')" />				<uni-list-item title="添加用户" @click="urlto('../../pages/adduser/adduser')" />				<uni-list-item title="用户管理" @click="urlto('../../pages/usermanage/usermanage')" />				<uni-list-item title="退出系统" @click="exitsystem" />			</uni-list>
			<!-- #endif -->			<!-- #ifndef MP-BAIDU || MP-ALIPAY || MP-TOUTIAO -->
			<uni-list v-if="identity == '用户'">
				<uni-list-item title="个人中心" @click="urlto('../../pages/userindex/userindex')" />				<uni-list-item title="退出系统" @click="exitsystem" />			</uni-list>
			<!-- #endif -->			</scroll-view>
		</uni-drawer>
		<uni-fab id="faban" :pattern="pattern" @fabClick="show('left')"></uni-fab>
	</view>
</template>
<script>
	import uniDrawer from '@/components/uni-drawer/uni-drawer.vue'
	import uniList from '@/components/uni-list/uni-list.vue'
	import uniListItem from '@/components/uni-list-item/uni-list-item.vue'
	import uniSection from '@/components/uni-section/uni-section.vue'
	import uniIcons from '@/components/uni-icons/uni-icons.vue'
	import uniFab from '@/components/uni-fab/uni-fab.vue';
	export default {
		components: {
			uniIcons,
			uniDrawer,
			uniList,
			uniListItem,
			uniSection,
			uniFab
		},
		data() {
			return {
				showRight: false,
				showLeft: false,
				pattern: {
					color: '#7A7E83',
					backgroundColor: '#fff',
					selectedColor: '#007AFF',
					buttonColor: '#007AFF'
				},
				identity: '',
				scviesty:'',
			}
		},
		methods: {
			exitsystem() {
				uni.clearStorage();
				uni.navigateTo({
					url: '../pages/login/login'
				})
			},
			urlto(url) {
				uni.redirectTo({
					url: url
				})
			},
			show(e) {
				let _this = this;
				
				uni.getStorage({
					key: 'identity',
					success(res) {
						console.log(res);
						_this.identity = res.data;
					}
				})
				
				uni.getSystemInfo({
				    success: function (res) {
				        console.log(res.windowHeight);
						_this.scviesty = "height:" + res.windowHeight + "px;";
				    }
				});
				
				console.log("show", e);
				if (e === 'left') {
					this.showLeft = true
				} else {
					this.showRight = true
				}
			},
			hide() {
				console.log("hide");
				this.showLeft = false;
				this.showRight = false;
			},
			closeDrawer(e) {
				if (e === 'left') {
					this.showLeft = false
				} else {
					this.showRight = false
				}
			},
			confirm() {}
		},
		onNavigationBarButtonTap(e) {
			this.showRight = !this.showRight
		},
		onBackPress() {
			if (this.showRight || this.showLeft) {
				this.hide()
				return true
			}
		},
		onLoad(e) {
			
			
		}
	}
</script>
<style>
	/* 头条小程序组件内不能引入字体 */
	/* #ifdef MP-TOUTIAO */
	@font-face {
		font-family: uniicons;
		font-weight: normal;
		font-style: normal;
		src: url('~@/static/uni.ttf') format('truetype');
	}
	/* #endif */
	/* #ifndef APP-NVUE */
	page {
		display: flex;
		flex-direction: column;
		box-sizing: border-box;
		min-height: 100%;
		height: auto;
	}
	view {
		font-size: 14px;
		line-height: inherit;
	}
	.example {
		padding: 0 15px 15px;
	}
	.example-info {
		padding: 15px;
		color: #3b4144;
		background: #ffffff;
	}
	.example-body {
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: center;
		padding: 0;
		font-size: 14px;
		background-color: #ffffff;
	}
	/* #endif */
	.example {
		padding: 0 15px;
	}
	.example-info {
		/* #ifndef APP-NVUE */
		display: block;
		/* #endif */
		padding: 15px;
		color: #3b4144;
		background-color: #ffffff;
		font-size: 14px;
		line-height: 20px;
	}
	.example-info-text {
		font-size: 14px;
		line-height: 20px;
		color: #3b4144;
	}
	.example-body {
		flex-direction: column;
		padding: 15px;
		background-color: #ffffff;
	}
	.word-btn-white {
		font-size: 18px;
		color: #FFFFFF;
	}
	.word-btn {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		align-items: center;
		justify-content: center;
		border-radius: 6px;
		height: 48px;
		margin: 15px;
		background-color: #007AFF;
	}
	.word-btn--hover {
		background-color: #4ca2ff;
	}
	.header {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		padding: 10px 15px;
		align-items: center;
		border-top-width: 1px;
		border-top-color: #f5f5f5;
		border-top-style: solid;
		background-color: #ffffff;
	}
	.input-view {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		align-items: center;
		flex-direction: row;
		background-color: #e7e7e7;
		height: 30px;
		border-radius: 15px;
		padding: 0 10px;
		flex: 1;
		background-color: #f5f5f5;
	}
	.uni-drawer-info {
		background-color: #ffffff;
		padding: 30rpx;
		padding-top: 10rpx;
		color: #3b4144;
	}
	.uni-padding-wrap {
		padding: 0 15px;
		line-height: 1.8;
	}
	.input {
		flex: 1;
		padding: 0 5px;
		height: 24px;
		line-height: 24px;
		font-size: 28rpx;
		background-color: transparent;
	}
	.close {
		padding: 30rpx;
	}
	.example-body {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		padding: 0;
	}
	.draw-cotrol-btn {
		flex: 1;
	}
</style>

