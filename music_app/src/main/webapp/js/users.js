const songList1 = [
	{
		id_song: 56,
		name: "Set fire to the rain",
		singer: "Adele",
		album: "No",
		dateAdded: "1000 day ago",
		image: "https://png.pngtree.com/png-vector/20220906/ourlarge/pngtree-qatar-world-cup-2022-png-image_6139489.png",
		path: "Music/SAKURA - いきものがかり (Ikimonogakari)(カヴァー) (320 kbps).mp3"
	},


	{
		id_song: 66,
		name: "Dau nho",
		singer: "Den Vau",
		album: "Lon xon",
		dateAdded: "4 day ago",
		image: "https://img.freepik.com/free-photo/3d-render-gift-box-with-ribbon-present-package_107791-14916.jpg?w=740&t=st=1669173113~exp=1669173713~hmac=f7a0e0d89c1bcfc2dea9e1afb587302f330b42cd5221a0d32fd819250752fcfc",
		path: "/Imagine Dragons & JID - Enemy (from the series Arcane_ League of Legends) _ Official Music Video (320 kbps).mp3"
	},
	{
		id_song: 76,
		name: "Freee",
		singer: "Sorry",
		album: "Love is gone",
		dateAdded: "1 day ago",
		image: "https://images.pexels.com/photos/1717969/pexels-photo-1717969.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=250&w=250",
		path: "/SAKURA - いきものがかり (Ikimonogakari)(カヴァー) (320 kbps).mp3"
	},


	{
		id_song: 896,
		name: "fall in love",
		singer: "Den Vau",
		album: "adsada",
		dateAdded: "6 day ago",
		image: "https://i.pinimg.com/564x/b0/a2/7c/b0a27cfa89f8764197d89e8268c861c6.jpg",
		path: "/Fairy Tail Theme Song - Extended (320 kbps).mp3",

	},
	{
		id_song: 12,
		name: "Mot trieu kha nang",
		singer: "youtube",
		album: "emix",
		dateAdded: "100 day ago",
		image: "https://i.pinimg.com/originals/89/4f/4b/894f4b2f803a5618a3c02795b361baa6.jpg",
		path: "/Một Triệu Khả Năng  Htrol Remix.mp3",

	},
	{
		id_song: 445,
		name: "Dau nho",
		singer: "Den Vau",
		album: "Lon xon",
		dateAdded: "6 day ago",
		image: "https://images.pexels.com/photos/1717969/pexels-photo-1717969.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=250&w=250",
		path: "/Darling in the FranXX OP_Opening Full「KISS OF DEATH - Mika Nakashima x Hyde」[Kami Cover] (320 kbps).mp3"
	},
	{
		id_song: 3,
		name: "Dau nho",
		singer: "Den Vau",
		album: "Lon xon",
		dateAdded: "6 day ago",
		image: "https://images.pexels.com/photos/2264753/pexels-photo-2264753.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=250&w=250",
		path: "/Adele - Set Fire To The Rain (Live at The Royal Albert Hall) (320 kbps).mp3"
	},
	{
		id_song: 1,
		name: " BAsla",
		singer: "Sing eeer ",
		album: "Lon xon",
		dateAdded: "9 day ago",
		image: "https://i.pinimg.com/564x/a4/5d/4b/a45d4bfd85859ad0005958a06b69feb4.jpg",
		path: "/いきものがかり 『ブルーバード』Music Video (320 kbps).mp3",

	},
	{
		id_song: 1,
		name: " BAsla",
		singer: "Sing eeer ",
		album: "Lon xon",
		dateAdded: "9 day ago",
		image: "https://scontent.fsgn7-1.fna.fbcdn.net/v/t39.30808-6/315906991_860932225331870_303287503299601786_n.jpg?stp=dst-jpg_p75x225&_nc_cat=100&ccb=1-7&_nc_sid=dbeb18&_nc_ohc=584jWJeHOnYAX_BHazl&_nc_ht=scontent.fsgn7-1.fna&oh=00_AfDy-ROtk7LKQ4WJ-NwQeCenLor8YELRww_qx4uifmZOrg&oe=6383382D",
		path: "/Christina Perri - A Thousand Years [Official Music Video] (320 kbps).mp3",

	},


]


const songIdList = document.getElementById("song-list")

const btnSeeMore = document.querySelector('.btn-see-more')

const footer = document.querySelector('.footer')
const songName = footer.querySelector('.info-form__name h5')
const songSinger = footer.querySelector('.info-form__name p')
const songImage = footer.querySelector('.info-form img')

const songLastDuration = footer.querySelector('.final-time span')
const songElementList = document.querySelectorAll('.list-item')


function myFunction() {
	//song_list, image_album, name_singer, listener.
}


var albums = null;
var songList = null;
let id_album = document.getElementById("id_album").textContent;
function readJSON() {
	/*	$.ajax({
			type: "GET",
			url: "/GetAblums",
			success: function(response) {
				loadAlbum(response);
			},
			async: false
		});
		*/
	//console.log("Call JSON");

	$.ajax({
		type: "GET",
		url: "/music_app/getSongUser",
		data: { id: id_album },
		dataType: 'text',
		success: function(response) {
			CalBackSong(response);
		},
		error: function(err) {
			console.log(err);
		},
		async: false
	});
}



function loadAlbum(response) {
	console.log("Album: " + response);
}

function CalBackSong(response) {
	songList = response;
	const json = String(songList);
	console.log(json);
	//var text = '[{"name":"Ai chung tinh duoc mai","name_singer":"Sơn Tùng M-TP","id":"15"},{"name":" safgd ydghjdfg sh rt oighhtyeh tjjnr r","name_singer":"Sơn Tùng M-TP","id":"16"}]';
	const myArray = JSON.stringify(json);
	var obj = myArray[0].name;
	console.log(myArray);
	//console.log(String(obj));
	return myArray;
}

//readJSON();
console.log(id_album);
console.log(songList);
songList = CalBackSong();
console.log(songList);

function truncate(text, number) {
	if (!text) return ''
	const limitText = text.split(' ').length
	// console.log(limitText, number)

	return limitText > number ? `${text.split(' ').filter(x => !!x && x.length >= 1).slice(0, number).join(' ')}...` : text
}

let songIndex = -1
let song_id = 0
//window.addEventListener('DOMContentLoaded',
function loadListSong() {

	const songListElement = songList.slice(0, 6).map((song, index) => {
		const divElement = document.createElement('div');
		divElement.dataset.id = index

		divElement.classList.add('list-item')

		const html = `
                <div class="list__id ">
                <i class="fa-solid fa-play list-play-music"></i>
                <i class="fa-solid fa-pause list-pause-music"></i>
            </div>
            <div class="list__title">
                <img src="${truncate(song.image)}" alt="" class="list__title-avatar">
                <div class="list__title-name-song">
                <div class="list__title-song">
                    <span  class="list__title-link-song">${truncate(song.name, 6)}</span> 
                </div>
                <div class="list__title-singer">
                <span class="list__title-link-singer">${song.singer}</span>
                </div>
                </div>
            </div>
            <div class="list__album">
                <a href="" class="list__album-name"><span>${song.album}</span></a>
            </div>
           
         `

		// console.log(divElement)
		divElement.innerHTML = html
		if (index === songIndex) {
			divElement.classList.add('active')
		}

		divElement.addEventListener('click', songItemClick)

		songIdList.appendChild(divElement)
	})

	// console.log(songListElement)

	//  songIdList.innerHTML = songListElement
}



if (btnSeeMore) {
	btnSeeMore.addEventListener('click', function(e) {
		const limitSong = btnSeeMore.textContent === 'SEE MORE' ? 100 : 5
		// console.log(limitSong)
		songIdList.innerHTML = ''
		const songListElement = songList.slice(0, limitSong).map((song, index) => {
			const divElement = document.createElement('div');
			divElement.dataset.id = index
			// div class="list-item" data-id="${index}">
			divElement.classList.add('list-item')

			const html = `
            <div class="list__id ">
            <i class="fa-solid fa-play list-play-music"></i>
            <i class="fa-solid fa-pause list-pause-music"></i>
        </div>
        <div class="list__title">
            <img src="${truncate(song.image)}" alt="" class="list__title-avatar">
            <div class="list__title-name-song">
            <div class="list__title-song">
                <span  class="list__title-link-song">${truncate(song.name, 6)}</span> 
            </div>
            <div class="list__title-singer">
            <span class="list__title-link-singer">${song.singer}</span>
            </div>
            </div>
        </div>
        <div class="list__album">
            <a href="" class="list__album-name"><span>${song.album}</span></a>
        </div>
     
             `

			// console.log(divElement)
			divElement.innerHTML = html
			console.log(songIndex)
			if (index === songIndex) {
				divElement.classList.add('active')
			}
			divElement.addEventListener('click', songItemClick)

			songIdList.appendChild(divElement)
		})

		if (btnSeeMore.textContent === 'SEE MORE') {
			btnSeeMore.innerHTML = 'SHOW LESS'
		} else {
			btnSeeMore.innerHTML = 'SEE MORE'
		}
	})
}


let playpause_btn = document.querySelector(".control-music");

let isPlaying = false;


function playpauseTrack() {
	if (!isPlaying) playTrack();
	else pauseTrack();
}

let curr_track = document.createElement('audio');

function playTrack() {
	curr_track.play();
	isPlaying = true;
	playpause_btn.innerHTML = '<i class="fa-solid fa-pause pause-music" ></i>';
}

function pauseTrack() {
	curr_track.pause();
	isPlaying = false;
	playpause_btn.innerHTML = '<i class="fa-solid fa-play pause-music "></i>';;
}

function loadTrack() {
	clearInterval(updateTimer);
	resetValues();
	updateTimer = setInterval(seekUpdate, 1000);
	curr_track.addEventListener("ended", nextTrack);

}

let volume_slider = document.querySelector(".volume_slider");


function setVolume() {
	curr_track.volume = volume_slider.value / 100;
}



function seekTo() {
	let seekto = curr_track.duration * (seek_slider.value / 100);
	curr_track.currentTime = seekto;
}

function seekUpdate() {
	let seekPosition = 0;

	if (!isNaN(curr_track.duration)) {
		seekPosition = curr_track.currentTime * (100 / curr_track.duration);

		seek_slider.value = seekPosition;

		let currentMinutes = Math.floor(curr_track.currentTime / 60);
		let currentSeconds = Math.floor(curr_track.currentTime - currentMinutes * 60);
		let durationMinutes = Math.floor(curr_track.duration / 60);
		let durationSeconds = Math.floor(curr_track.duration - durationMinutes * 60);

		if (currentSeconds < 10) { currentSeconds = "0" + currentSeconds; }
		if (durationSeconds < 10) { durationSeconds = "0" + durationSeconds; }
		if (currentMinutes < 10) { currentMinutes = "0" + currentMinutes; }
		if (durationMinutes < 10) { durationMinutes = "0" + durationMinutes; }

		curr_time.textContent = currentMinutes + ":" + currentSeconds;
		total_duration.textContent = durationMinutes + ":" + durationSeconds;
	}
}

function resetClassSongItem() {
	const songElement = document.querySelectorAll('.list-item')
	songElement.forEach(function(song) {
		song.classList.remove('active')
	})
	resetValues()

}
const total_duration = footer.querySelector('.now-time span')
const seek_slider = footer.querySelector('.seek_slider')
const curr_time = footer.querySelector('.final-time span')

function resetValues() {
	seek_slider.value = 0;
	total_duration.textContent = "00:00";
	curr_time.textContent = "00:00";
}


let track_index = 0;

let updateTimer;

// them phan phat nhac

function updateSong(song) {
	const songNameItem = song.querySelector('.list__title-link-song')
	const songSingerItem = song.querySelector('.list__title-link-singer')
	const songImageItem = song.querySelector('.list__title-avatar')
	const songDurationItem = song.querySelector('.list__time-time-song')
	songName.innerHTML = songNameItem.textContent
	songSinger.innerHTML = songSingerItem.textContent
	songImage.src = songImageItem.src

	// songLastDuration.innerHTML = songDurationItem.textContent

	song.classList.add('active')
	song_id = songList[songIndex].id_song;

	console.log("Song id: " + song_id)

	curr_track.src = songList[songIndex].path;
	curr_track.load();

	updateTimer = setInterval(seekUpdate, 1000);
	curr_track.addEventListener("ended", nextTrack);
	pauseTrack()
	playpauseTrack()
}


// chon bai 
function songItemClick(e) {
	resetClassSongItem()
	resetValues()
	const index = e.currentTarget.dataset.id

	songIndex = Number.parseInt(index)
	console.log("id:" + songIndex)


	const listItem = e.currentTarget
	updateSong(listItem)


}

function nextTrack() {
	const songItem = document.querySelector(`.list-item.active`)
	// console.log(songItem)
	songIndex = songItem ? songItem.dataset.id : songIndex
	// console.log(id)
	let index = Number.parseInt(songIndex) + 1
	songIndex = index
	// console.log(index)
	const songListElementBtn = document.querySelectorAll('.list-item')
	if (songIndex > songListElementBtn.length - 1) {
		songIndex = 0
		index = 0
	}
	const songNext = document.querySelector(`[data-id="${index}"]`)
	resetClassSongItem()
	updateSong(songNext)

	// if (track_index < track_list.length - 1)
	//   track_index += 1;
	// else track_index = 0;

}


// tien len truoc
const btnNext = document.querySelector('.next-music')

btnNext.addEventListener('click', () => {
	const songItem = document.querySelector(`.list-item.active`)
	// console.log(songItem)
	songIndex = songItem ? songItem.dataset.id : songIndex
	console.log(songIndex)
	let index = Number.parseInt(songIndex) + 1
	songIndex = index
	// console.log(index)
	const songListElementBtn = document.querySelectorAll('.list-item')
	if (songIndex > songListElementBtn.length - 1) {
		songIndex = 0
		index = 0
	}
	const songNext = document.querySelector(`[data-id="${index}"]`)
	resetClassSongItem()
	updateSong(songNext)

})
// quay lai bai truoc
const btnPrev = document.querySelector('.back-music')
btnPrev.addEventListener('click', () => {
	const songItem = document.querySelector(`.list-item.active`)
	// console.log(songItem)
	songIndex = songItem ? songItem.dataset.id : songIndex
	// console.log(id)
	let index = Number.parseInt(songIndex) - 1
	songIndex = index
	console.log(index)
	const songListElementBtn = document.querySelectorAll('.list-item')
	if (songIndex < 0) {
		songIndex = songListElementBtn.length - 1
		index = songListElementBtn.length - 1
	}
	const songNext = document.querySelector(`[data-id="${index}"]`)
	resetClassSongItem()
	updateSong(songNext)

})