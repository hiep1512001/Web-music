
const songList = [
    {
        name: "Dau nho",
        singer: "Den Vau",
        album: "Lon xon",
        dateAdded: "6 day ago",
        duration: 3
    },
    {
        name: "Dau nho",
        singer: "Den Vau",
        album: "Lon xon",
        dateAdded: "6 day ago",
        duration: 3
    },
    {
        name: "Dau nho",
        singer: "Den Vau",
        album: "Lon xon",
        dateAdded: "6 day ago",
        duration: 3
    },
    {
        name: "Dau nho",
        singer: "Den Vau",
        album: "Lon xon",
        dateAdded: "6 day ago",
        duration: 3
    },
    {
        name: "Dau nho",
        singer: "Den Vau",
        album: "Lon xon",
        dateAdded: "9 day ago",
        duration: 3
    },
    {
        name: "Dau nho",
        singer: "Den Vau",
        album: "Lon xon",
        dateAdded: "6 day ago",
        duration: 3
    },
    {
        name: "Dau nho",
        singer: "Den Vau",
        album: "Lon xon",
        dateAdded: "5 day ago",
        duration: 3
    },
    {
        name: "Dau nho",
        singer: "Den Vau",
        album: "Lon xon",
        dateAdded: "4 day ago",
        duration: 3
    },
    {
        name: "Dau nho",
        singer: "Den Vau",
        album: "Lon xon",
        dateAdded: "1 day ago",
        duration: 3
    },
    {
        name: "Dau nho",
        singer: "Den Vau",
        album: "Lon xon",
        dateAdded: "2 day ago",
        duration: 3
    },
    {
        name: "Dau nho",
        singer: "Den Vau",
        album: "adsada",
        dateAdded: "6 day ago",
        duration: 3
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



function truncate(text, number){
    if(!text) return ''
    const limitText = text.split(' ').length
    // console.log(limitText, number)
    
    return limitText > number ? `${text.split(' ').filter(x => !!x && x.length >= 1).slice(0, number).join(' ')}...` : text
}

let songIndex = -1

window.addEventListener('DOMContentLoaded', function(){
    const songListElement = songList.slice(0, 5).map((song, index) => {
    const divElement = document.createElement('div');
    divElement.dataset.id = index
            // div class="list-item" data-id="${index}">
            divElement.classList.add('list-item')

        const html =  `
    <!-- list item -->
    <div class="list__id ">
        <p>1</p>
        <i class="fa-solid fa-play list-play-music"></i>
        <i class="fa-solid fa-pause list-pause-music"></i>
    </div>

    <div class="list__title">
        <img src="../image/den1.jpg" alt="" class="list__title-avatar">
        <div class="list__title-name-song">
        <div class="list__title-song">
            <a href="" class="list__title-link-song"><span>${truncate(song.name, 6)}</span> </a>
        </div>
        <div class="list__title-singer">
            <a href="" class="list__title-link-singer"><span>${song.singer}</span></a>
        </div>
        </div>
    </div>
    <div class="list__album">
        <a href="" class="list__album-name"><span>${song.album}</span></a>
    </div>
    <div class="list__dateAdd">
        <p class="list__dateAdd-date">${song.dateAdded}</p>
    </div>
    <div class="list__time">
        <p class="list__time-time-song">${song.duration}</p>
    </div>
    `

    // console.log(divElement)
    divElement.innerHTML = html
    if(index === songIndex){
        divElement.classList.add('active')
    }
    divElement.addEventListener('click', songItemClick)

    songIdList.appendChild(divElement)})

    // console.log(songListElement)

    //  songIdList.innerHTML = songListElement
})



if(btnSeeMore){
    btnSeeMore.addEventListener('click', function(e){
        const limitSong = btnSeeMore.textContent === 'SEE MORE' ? 100 : 5
        // console.log(limitSong)
        songIdList.innerHTML = ''
        const songListElement = songList.slice(0 , limitSong).map((song, index) => {
            const divElement = document.createElement('div');
            divElement.dataset.id = index
            // div class="list-item" data-id="${index}">
            divElement.classList.add('list-item')

        const html =  `
    <!-- list item -->
    <div class="list__id ">
        <p>1</p>
        <i class="fa-solid fa-play list-play-music"></i>
        <i class="fa-solid fa-pause list-pause-music"></i>
    </div>

    <div class="list__title">
        <img src="../image/den1.jpg" alt="" class="list__title-avatar">
        <div class="list__title-name-song">
        <div class="list__title-song">
            <a href="" class="list__title-link-song"><span>${truncate(song.name, 6)}</span> </a>
        </div>
        <div class="list__title-singer">
            <a href="" class="list__title-link-singer"><span>${song.singer}</span></a>
        </div>
        </div>
    </div>
    <div class="list__album">
        <a href="" class="list__album-name"><span>${song.album}</span></a>
    </div>
    <div class="list__dateAdd">
        <p class="list__dateAdd-date">${song.dateAdded}</p>
    </div>
    <div class="list__time">
        <p class="list__time-time-song">${song.duration}</p>
    </div>
    `

    // console.log(divElement)
    divElement.innerHTML = html
    console.log(songIndex)
    if(index === songIndex){
        divElement.classList.add('active')
    }
    divElement.addEventListener('click', songItemClick)

    songIdList.appendChild(divElement)
})



     if(btnSeeMore.textContent === 'SEE MORE'){
        btnSeeMore.innerHTML = 'SHOW LESS'
    } else{
         btnSeeMore.innerHTML = 'SEE MORE'
     }
    })
}


function resetClassSongItem(){
    const songElement = document.querySelectorAll('.list-item')
    songElement.forEach( function(song) {
        song.classList.remove('active')
    })
}

function updateSong (song) {
    const songNameItem = song.querySelector('.list__title-link-song span')
    const songSingerItem = song.querySelector('.list__title-link-singer span')
    const songImageItem = song.querySelector('.list__title-avatar')
    const songDurationItem = song.querySelector('.list__time-time-song')
    songName.innerHTML = songNameItem.textContent
    songSinger.innerHTML = songSingerItem.textContent
    songImage.src = songImageItem.src
    
    
    songLastDuration.innerHTML = songDurationItem.textContent
    
    
    song.classList.add('active')
}

function songItemClick(e){
    resetClassSongItem()
    const index = e.currentTarget.dataset.id
    songIndex = Number.parseInt(index)
    
    const listItem = e.currentTarget
    updateSong(listItem)
}



const btnNext = document.querySelector('.next-music')
btnNext.addEventListener('click', () => {
    const songItem = document.querySelector(`.list-item.active`)
    // console.log(songItem)
    songIndex = songItem ? songItem.dataset.id : songIndex
    // console.log(id)
    let index = Number.parseInt(songIndex) + 1
    songIndex = index
    // console.log(index)
    const songListElementBtn = document.querySelectorAll('.list-item')
    if(songIndex > songListElementBtn.length - 1) {
        songIndex = 0
        index = 0
    }
    const songNext = document.querySelector(`[data-id="${index}"]`)
    resetClassSongItem()
    updateSong(songNext)
})

const btnPrev = document.querySelector('.back-music')
btnPrev.addEventListener('click', () => {
    const songItem = document.querySelector(`.list-item.active`)
    // console.log(songItem)
    songIndex = songItem ? songItem.dataset.id : songIndex
    // console.log(id)
    let index = Number.parseInt(songIndex) - 1
    songIndex = index
    // console.log(index)
    const songListElementBtn = document.querySelectorAll('.list-item')
    if(songIndex < 0) {
        songIndex = songListElementBtn.length - 1
        index = songListElementBtn.length - 1
    }
    const songNext = document.querySelector(`[data-id="${index}"]`)
    resetClassSongItem()
    updateSong(songNext)
})

function edit_user(){
    const fullname = document.getElementById('myName');
    const pass = document.getElementById('myPassword');
    const unhiden = document.getElementById('unhiden');
    const edit = document.getElementById('edit');
    const save = document.getElementById('save');

    fullname.disabled = false;
    pass.disabled = false;
    edit.className = 'none-btn';
    save.classList.remove('none-btn');
    unhiden.classList.remove('none-btn');
}

function save_user(){
    const fullname = document.getElementById('myName');
    const pass = document.getElementById('myPassword');
    const unhiden = document.getElementById('unhiden');
    const edit = document.getElementById('edit');
    const save = document.getElementById('save');
    fullname.disabled = true;
    pass.disabled = true;
    save.className = 'none-btn';
    unhiden.className = 'none-btn';
    edit.classList.remove('none-btn');
}




