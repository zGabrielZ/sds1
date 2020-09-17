import moment from 'moment'

export const formData = (date:string) => {
    return moment(date).format('DD/MM/YYYY HH:mm')
}