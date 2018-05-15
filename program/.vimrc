" Set this to 1 to begin recording 
let record=0

" This is the heart of it all
function REC(record)
  if a:record
    " Set up some 
    let fname = expand('%:p') . ".vrec"
    let lineCount = line('$')
    let pos = getpos(".")

    " Make sure the file exists and has proper write permissions
    call system('touch ' . fname)
    call system('chmod u+w ' . fname)

    " Append the frame "header" for this edit to the vrec file
    call system('echo ' . lineCount . '~$(($(date +%s%N)/1000000))~' . pos[1] . '~' . pos[2] . ' >> ' . fname)

    " Record this edit, appending it to the vrec file
    execute 'silent! w >> ' . fname
  endif
endfunction

" Autocmd hooks
autocmd CursorMoved * call REC(record)
autocmd InsertLeave * call REC(record)
autocmd TextChanged * call REC(record)
